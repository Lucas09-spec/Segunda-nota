package com.resena.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resena.model.Resena;
import com.resena.service.ResenaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/Resena")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping
    public ResponseEntity<List<Resena>> obtenerResena() {
        List<Resena> lista = resenaService.getResenas();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resena> obtenerResenaPorId(@PathVariable("id") Long id) {
        try {
            Resena resena = resenaService.getResenaPorId(id);
            return ResponseEntity.ok(resena);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarResena(@RequestBody Resena nuevo) {
        // Validaciones previas al llamado al servicio
        if (nuevo == null) {
            return ResponseEntity.badRequest().body("La reseña no puede estar vacía.");
        }

        if (nuevo.getComentario() == null || nuevo.getComentario().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El comentario es obligatorio.");
        }

        if (nuevo.getF_com() == null) {
            return ResponseEntity.badRequest().body("La fecha del comentario es obligatoria.");
        }

        if (nuevo.getId_Usuario() == null) {
            return ResponseEntity.badRequest().body("El ID del usuario es obligatorio.");
        }

        if (nuevo.getId_servicio() == null) {
            return ResponseEntity.badRequest().body("El ID del servicio es obligatorio.");
        }

        if (nuevo.getNota() == null || nuevo.getNota() < 1 || nuevo.getNota() > 5) {
            return ResponseEntity.badRequest().body("La nota debe estar entre 1 y 5.");
        }

        try {
            Resena resenaGuardado = resenaService.saveResena(nuevo);
            return ResponseEntity.status(201).body(resenaGuardado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocurrió un error interno al guardar la reseña.");
        }
    }
}
