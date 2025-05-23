package com.Direccion.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Direccion.Model.Comuna;
import com.Direccion.Model.Direccion;
import com.Direccion.Repository.ComRepository;
import com.Direccion.Repository.DireccionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class ComService {

    @Autowired
    private ComRepository comRepository;

    public List<Comuna> getComunas(){
        return comRepository.findAll();

    }


    public Comuna getComunaPorId(Long Id){
        return comRepository.findById(Id)
        .orElseThrow(()-> new RuntimeException("Comuna no encontrada"));
    }


    public Comuna saveComuna(Comuna nuevo){
        if (nuevo.getId_com() == null || nuevo.getNom_com() == null || nuevo.getId_reg() == null){
            throw new IllegalArgumentException("Todos los campos son obligatorios");

        }
        return comRepository.save(nuevo);
    }
}
