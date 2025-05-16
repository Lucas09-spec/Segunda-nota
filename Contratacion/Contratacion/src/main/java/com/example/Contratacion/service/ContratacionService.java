package com.example.Contratacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Contratacion.model.ContratacionModel;
import com.example.Contratacion.repository.ContratacionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ContratacionService {
    @Autowired
    private ContratacionRepository contratacionRepository;

    public List<ContratacionModel> getClientes(){
        return contratacionRepository.findAll();
    }

    public ContratacionModel getContratacionId(Long id){
        return contratacionRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Contrato No Encontrado"));
    }

    public ContratacionModel saveContratacion(ContratacionModel nuevo){
        return contratacionRepository.save(nuevo);
    }

}
