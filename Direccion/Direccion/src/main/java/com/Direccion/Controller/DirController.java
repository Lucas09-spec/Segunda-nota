package com.Direccion.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Direccion.Model.Direccion;
import com.Direccion.Service.DirService;

@RestController
@RequestMapping("api/v1/Direccion")
public class DirController {

    @Autowired
    private DirService dirService;

    @GetMapping
    public ResponseEntity<List<Direccion>> obtenerDir() {
        List<Direccion> lista = dirService.getDireccion();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Direccion> obtenerDireccionPorId(@PathVariable Long id) {
        try {
            Direccion direccion = dirService.getDireccionPorId(id);
            return ResponseEntity.ok(direccion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

  @PostMapping
public ResponseEntity<Direccion> guardarDir(@RequestBody Direccion nuevo) {
    try {
        Direccion direccionGuardada = dirService.saveDireccion(nuevo);
        System.out.println(direccionGuardada); // Verifica qué datos están siendo guardados
        return ResponseEntity.status(201).body(direccionGuardada);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(400).body(null); // Bad Request
    } catch (Exception e) {
        return ResponseEntity.status(500).body(null); // Internal Server Error
    }
}



}
