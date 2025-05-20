package com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Contrato;
import com.service.ContratoService;

@RestController
@RequestMapping("/api/v1/contrato")

public class ContratoController {


    @Autowired
    private ContratoService contratoService;

    @GetMapping
    public ResponseEntity<List<Contrato>> obtenerContratos(){
        List<Contrato> lista = contratoService.getContratos();
        if (lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }
          return ResponseEntity.ok(lista);        
    }

        @GetMapping("/{id}")
    public ResponseEntity<Contrato> obtenerContratoporId(@PathVariable Long id){
   try {
            Contrato contrato = contratoService.getContratoPorId(id); 
            return ResponseEntity.ok(contrato);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }







    

       @PostMapping
    public ResponseEntity<Contrato> guardarContrato(@RequestBody Contrato nuevo) {
        try {
            Contrato clienteGuardado = contratoService.saveContrato(nuevo); // Llamada correcta
            System.out.println(clienteGuardado); // Para verificar en consola
            return ResponseEntity.status(201).body(clienteGuardado); // HTTP 201 Created
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null); // Bad Request
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Internal Server Error
        }
    }
}

