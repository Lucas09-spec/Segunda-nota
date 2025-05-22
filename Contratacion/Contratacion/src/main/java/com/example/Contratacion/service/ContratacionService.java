package com.example.Contratacion.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Contratacion.model.ContratacionModel;
import com.example.Contratacion.repository.ContratacionRepository;
import com.example.Contratacion.webusuario.UsuarioUsuar;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ContratacionService {
    @Autowired
    private ContratacionRepository contratacionRepository;

    @Autowired
    private UsuarioUsuar usuarioUsuar;

    public List<ContratacionModel> getContratacion(){
        return contratacionRepository.findAll();
    }

    public ContratacionModel saveContratacion(ContratacionModel nuevoContratacion){
        Map<String,Object> usuario = usuarioUsuar.getClienteById(nuevoContratacion.getUsuarioId());

        if(usuario == null || usuario.isEmpty()){
            throw new RuntimeException("Usuario no encontrado. No se peude agreagar Contratacion");

        }
        return contratacionRepository.save(nuevoContratacion);
    }

}
