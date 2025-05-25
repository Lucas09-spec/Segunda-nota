package com.example.Soporte.controller;

import java.util.List;

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
    private final SoporteService soporteService;

    @Autowired
    public SoporteController(SoporteService soporteService) {
        this.soporteService = soporteService;
    }

    @GetMapping
    public ResponseEntity<List<Soporte>> getSoportes() {
        List<Soporte> soportes = soporteService.findAll();
        return ResponseEntity.ok(soportes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Soporte> getSoporteById(@PathVariable Long id) {
        return soporteService.findById(id)
                .map(ResponseEntity::ok)                 // Si se encuentra el soporte, retorna 200 OK con el objeto
                .orElse(ResponseEntity.notFound().build()); // Si no se encuentra, retorna 404 Not Found
    }

    @PostMapping
    public ResponseEntity<Soporte> createSoporte(@RequestBody Soporte soporte) {
        Soporte newSoporte = soporteService.create(soporte);
        return ResponseEntity.ok(newSoporte);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Soporte> updateSoporte(@PathVariable Long id, @RequestBody Soporte soporte) {
        Soporte updatedSoporte = soporteService.update(id, soporte);
        return ResponseEntity.ok(updatedSoporte);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSoporte(@PathVariable Long id) {
        soporteService.delete(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content para indicar que se eliminó correctamente
    }

}
