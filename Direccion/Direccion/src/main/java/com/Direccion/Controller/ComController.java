package com.Direccion.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Direccion.Model.Comuna;
import com.Direccion.Service.ComService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/Comuna")


public class ComController {

    @Autowired
    private ComService comService;

    @GetMapping
    public ResponseEntity<List<Comuna>> obtenerComuna(){
        List<Comuna> lista = comService.getComunas();
        if(lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{id}")
    public ResponseEntity <Comuna> obtenerComunaPorId(@PathVariable Long Id){
        try{
            Comuna comuna = comService.getComunaPorId(Id);
            return ResponseEntity.ok(comuna);

        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Comuna> guardarComuna(@RequestBody Comuna nuevo){
        try{
            Comuna comunaGuardar = comService.saveComuna(nuevo);
            System.out.println(comunaGuardar);
            return ResponseEntity.status(201).body(comunaGuardar);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(400).body(null);

        }catch (Exception e ){
            return ResponseEntity.status(500).body(null);
        }
    }


}
