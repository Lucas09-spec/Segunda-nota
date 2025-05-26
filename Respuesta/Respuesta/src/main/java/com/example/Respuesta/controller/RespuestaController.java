package com.example.Respuesta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<List<Respuesta>> getAllRespuesta() {
        List<Respuesta> respuestas = respuestaService.findAll();
        return ResponseEntity.ok(respuestas);
    }

    

}
