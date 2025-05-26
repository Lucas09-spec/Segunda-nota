package com.Proyecto.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Proyecto.Model.Proyecto;
import com.Proyecto.Service.ProyectoService;
@RestController
@RequestMapping("/api/v1/Proyecto")




public class ProyectoController {
    @Autowired
    private ProyectoService proyectoService;

    @GetMapping
    public ResponseEntity<List<Proyecto>> obtenerProyecto(){
        List<Proyecto> lista = proyectoService.getProyectos();
        if (lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }


    @PostMapping
    public ResponseEntity<Proyecto> GuardarProyecto(@RequestBody Proyecto nuevo){
       try{
        Proyecto ProyectoGuardado = proyectoService.saveProyecto(nuevo);
        System.out.println(ProyectoGuardado);
        return ResponseEntity.status(201).body(ProyectoGuardado);
    }catch (IllegalArgumentException e){
        return ResponseEntity.status(400).body(null);
    } catch (Exception e){
        return ResponseEntity.status(500).body(null);
    }
    }


    



}
