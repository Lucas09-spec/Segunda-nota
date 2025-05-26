package com.example.Respuesta.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Respuesta.model.Respuesta;
import com.example.Respuesta.service.RespuestaService;

@RestController
@RequestMapping("/api/v1/respuestas")
public class RespuestaController {
    @Autowired
    private RespuestaService respuestaService;
    
    @GetMapping
    public ResponseEntity<List<Respuesta>> getAllRespuestas(){
        List<Respuesta> respuestas = respuestaService.findAll();
        return ResponseEntity.ok(respuestas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Respuesta> getRespuestaById(@PathVariable Long id) {
        Optional<Respuesta> respuesta = respuestaService.findById(id);
        return respuesta.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }    

    @PostMapping
    public ResponseEntity<Respuesta> createRespuesta(@RequestBody Respuesta nuevoRespuesta) {
        try {
            Respuesta respuestaGuardado = respuestaService.saveSoporte(nuevoRespuesta);
            return ResponseEntity.ok(respuestaGuardado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Respuesta> updateRespuesta(@PathVariable Long id, @RequestBody Respuesta respuestaActualizado) {
        Optional<Respuesta> respuestaExistente = respuestaService.findById(id);

        if (respuestaExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Asegurar que se mantenga el mismo ID
        respuestaActualizado.setId(id);

        try {
            Respuesta respuestaGuardado = respuestaService.saveSoporte(respuestaActualizado);
            return ResponseEntity.ok(respuestaGuardado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
