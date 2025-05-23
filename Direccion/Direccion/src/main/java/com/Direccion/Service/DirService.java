package com.Direccion.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Direccion.Model.Direccion;
import com.Direccion.Repository.DireccionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class DirService {

    @Autowired
    private DireccionRepository direccionRepository;

    public List<Direccion> getDireccion(){
        return direccionRepository.findAll();
    }


    public Direccion getDireccionPorId(Long Id){
        return direccionRepository.findById(Id)
        .orElseThrow(()-> new RuntimeException("Todos los campos son obligatorios"));

    }

    public Direccion saveDireccion (Direccion nuevo){
        if (nuevo.getId_com() == null|| nuevo.getNom_dir() == null ){
            throw new IllegalArgumentException("Todos los argumentos deben ser obligatorios");

        }
       return direccionRepository.save(nuevo);
        
    }

}
