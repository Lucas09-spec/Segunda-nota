package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Repository.ContratoRepository;
import com.model.Contrato;

import jakarta.transaction.Transactional;

@Service
@Transactional


public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    public List<Contrato> getContratos(){
        return contratoRepository.findAll();
    }


    public Contrato getContratoPorId(Long Id){
        return contratoRepository.findById(Id)
        .orElseThrow(()-> new RuntimeException("Contrato no encontrado"));

    }

   public Contrato saveContrato(Contrato nuevo) {
    if (nuevo.getFecha_inicio() == null || 
        nuevo.getFecha_final() == null || 
        nuevo.getFecha_contrato() == null || 
        nuevo.getTotal() == null || 
        nuevo.getId() == null) {
        
        // Lógica en caso de que falte información
        throw new IllegalArgumentException("Faltan datos obligatorios del contrato.");
    }

    // Guardar contrato
    return contratoRepository.save(nuevo);
}

}
