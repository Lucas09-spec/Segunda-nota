package com.Proyecto.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Proyecto.Model.Proyecto;
import com.Proyecto.Service.ProyectoService;

@RestController
@RequestMapping("/api/v1/proyecto")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping
    public ResponseEntity<List<Proyecto>> obtenerProyectos() {
        List<Proyecto> lista = proyectoService.getProyectos();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProyectoPorId(@PathVariable Long id) {
        Optional<Proyecto> proyecto = proyectoService.getProyectoById(id);
        if (proyecto.isPresent()) {
            return ResponseEntity.ok(proyecto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarProyecto(@RequestBody Proyecto nuevo) {
        try {
            if (nuevo.getComentarios() == null || nuevo.getComentarios().isBlank()) {
                return ResponseEntity.badRequest().body("El campo 'comentarios' es obligatorio.");
            }
            if (nuevo.getId_contrato() == null || nuevo.getId_contrato() <= 0) {
                return ResponseEntity.badRequest().body("El campo 'Id_contrato' debe ser positivo.");
            }
            if (nuevo.getId_tecnico() == null || nuevo.getId_tecnico() <= 0) {
                return ResponseEntity.badRequest().body("El campo 'Id_tecnico' debe ser positivo.");
            }
            if (nuevo.getId_estado() == null || nuevo.getId_estado() <= 0) {
                return ResponseEntity.badRequest().body("El campo 'Id_estado' debe ser positivo.");
            }
            if (nuevo.getFecha() != null && nuevo.getFecha().isAfter(java.time.LocalDate.now())) {
                return ResponseEntity.badRequest().body("La fecha no puede ser futura.");
            }

            Proyecto guardado = proyectoService.saveProyecto(nuevo);
            return ResponseEntity.ok(guardado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al guardar el proyecto: " + e.getMessage());
        }
    }

    @PutMapping("/asignar-tecnico/{id}")
    public ResponseEntity<?> asignarTecnico(@PathVariable Long id, @RequestParam Long idTecnico) {
        Optional<Proyecto> proyectoOpt = proyectoService.getProyectoById(id);
        if (proyectoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Proyecto proyecto = proyectoOpt.get();
        proyecto.setId_tecnico(idTecnico);
        try {
            proyectoService.saveProyecto(proyecto);
            return ResponseEntity.ok("Técnico asignado correctamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProyecto(@PathVariable Long id) {
        Optional<Proyecto> proyecto = proyectoService.getProyectoById(id);
        if (proyecto.isPresent()) {
            proyectoService.deleteProyecto(id);
            return ResponseEntity.ok("Proyecto eliminado correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
} 
