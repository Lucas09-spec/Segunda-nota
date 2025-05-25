package com.example.Respuesta.controller;

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

import com.example.Respuesta.model.Respuesta;
import com.example.Respuesta.service.RespuestaService;

@RestController
@RequestMapping("/api/v1/respuestas")
public class RespuestaController {
    private final RespuestaService respuestaService;

    @Autowired
    public RespuestaController(RespuestaService respuestaService) {
        this.respuestaService = respuestaService;
    }

    @GetMapping
    public ResponseEntity<List<Respuesta>> getRespuestas() {
        List<Respuesta> respuestas = respuestaService.findAll(); 
        return ResponseEntity.ok(respuestas);                     
    }

    @GetMapping("/{id}")
    public ResponseEntity<Respuesta> getRespuestaById(@PathVariable Long id) {
        return respuestaService.findById(id)
                .map(ResponseEntity::ok)                         
                .orElse(ResponseEntity.notFound().build());      
    }

    @PostMapping
    public ResponseEntity<Respuesta> createRespuesta(@RequestBody Respuesta respuesta) {
        Respuesta newRespuesta = respuestaService.create(respuesta); 
        return ResponseEntity.ok(newRespuesta);                      
    }

    @PutMapping("/{id}")
    public ResponseEntity<Respuesta> updateRespuesta(@PathVariable Long id, @RequestBody Respuesta respuesta) {
        Respuesta updatedRespuesta = respuestaService.update(id, respuesta); 
        return ResponseEntity.ok(updatedRespuesta);                           
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRespuesta(@PathVariable Long id) {
        respuestaService.delete(id);                           
        return ResponseEntity.noContent().build();             
    }

}
