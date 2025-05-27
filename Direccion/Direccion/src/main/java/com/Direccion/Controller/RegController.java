package com.Direccion.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Direccion.Model.Region;
import com.Direccion.Service.RegService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
@RestController
@RequestMapping("/api/v1/Region")

public class RegController {
    @Autowired 
    private RegService regService;

    @GetMapping
    public ResponseEntity <List<Region>> obtenerRegion(){
        List<Region> lista = regService.getRegions();
        if (lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Region> obtenerRegionPorId(@PathVariable Long Id){
        try {
            Region region = regService.getRegionPorId(Id);
            return ResponseEntity.ok(region);

        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity <Region> guardarRegion(@RequestBody Region nuevo){
     try {   Region regionguardado = regService.saveRegion(nuevo);
        System.out.println(regionguardado);
        return ResponseEntity.status(201).body(regionguardado);
     } catch (IllegalArgumentException e ){
        return ResponseEntity.status(400).body(null);

     }catch (Exception e){
        return ResponseEntity.status(500).body(null);
     }
    
    } 
    





}



