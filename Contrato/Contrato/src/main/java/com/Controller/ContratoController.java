package com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.model.Contrato;
import com.service.ContratoService;
import com.Webproyecto.WebUsuario;
import com.Webproyecto.ServicioClient;
import com.Webproyecto.DireccionClient;

@RestController
@RequestMapping("/api/v1/contrato")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @Autowired
    private WebUsuario webUsuario;

    @Autowired
    private ServicioClient servicioClient;

    @Autowired
    private DireccionClient direccionClient;

    @GetMapping
    public ResponseEntity<List<Contrato>> obtenerContratos() {
        List<Contrato> lista = contratoService.getContratos();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrato> obtenerContratoporId(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            Contrato contrato = contratoService.getContratoPorId(id);
            return ResponseEntity.ok(contrato);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarContrato(@RequestBody Contrato nuevo) {
        if (nuevo == null) {
            return ResponseEntity.badRequest().body("El contrato no puede ser nulo.");
        }

        if (nuevo.getFecha_inicio() == null ||
            nuevo.getFecha_final() == null ||
            nuevo.getFecha_contrato() == null ||
            nuevo.getTotal() == null ||
            nuevo.getId_direcc() == null ||
            nuevo.getId_usuario() == null ||
            nuevo.getId_servicio() == null) {
            return ResponseEntity.badRequest().body("Faltan campos obligatorios.");
        }

        if (!webUsuario.existeUsuario(nuevo.getId_usuario())) {
            return ResponseEntity.badRequest().body("El ID de usuario no existe.");
        }

        if (!servicioClient.existeServicio(nuevo.getId_servicio())) {
            return ResponseEntity.badRequest().body("El ID de servicio no existe.");
        }

        if (!direccionClient.existeDireccion(nuevo.getId_direcc())) {
            return ResponseEntity.badRequest().body("El ID de dirección no existe.");
        }

        try {
            Contrato contratoGuardado = contratoService.saveContrato(nuevo);
            return ResponseEntity.status(201).body(contratoGuardado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor.");
        }
    }
}
