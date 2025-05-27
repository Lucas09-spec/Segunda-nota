package com.example.Soporte.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Soporte.model.Soporte;
import com.example.Soporte.service.SoporteService;



@RestController
@RequestMapping("/api/v1/soportes")
public class SoporteController {
    
    @Autowired
    private SoporteService soporteService;

    @GetMapping
    public ResponseEntity<List<Soporte>> getAllSoportes() {
        List<Soporte> soportes = soporteService.findAll();
        return ResponseEntity.ok(soportes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Soporte> getSoporteById(@PathVariable Long id) {
        Optional<Soporte> soporte = soporteService.findById(id);
        return soporte.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Soporte> createSoporte(@RequestBody Soporte nuevoSoporte) {
        try {
            Soporte soporteGuardado = soporteService.saveSoporte(nuevoSoporte);
            return ResponseEntity.ok(soporteGuardado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Soporte> updateSoporte(@PathVariable Long id, @RequestBody Soporte soporteActualizado) {
        Optional<Soporte> soporteExistente = soporteService.findById(id);

        if (soporteExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Asegurar que se mantenga el mismo ID
        soporteActualizado.setId(id);

        try {
            Soporte soporteGuardado = soporteService.saveSoporte(soporteActualizado);
            return ResponseEntity.ok(soporteGuardado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSoporte(@PathVariable Long id) {
        soporteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
