package com.example.Contratacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Contratacion.model.ContratacionModel;
import com.example.Contratacion.service.ContratacionService;

@RestController
@RequestMapping("/api/v1/contratacion")
public class ContratacionController {
    @Autowired
    private ContratacionService contratacionService;

    @GetMapping
    public ResponseEntity<List<ContratacionModel>> obtenerContratacion(){
        List<ContratacionModel> lista = contratacionService.getContratacion();
        if(lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }
    }

}
