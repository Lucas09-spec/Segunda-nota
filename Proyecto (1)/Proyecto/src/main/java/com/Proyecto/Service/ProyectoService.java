package com.Proyecto.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Proyecto.Model.Proyecto;
import com.Proyecto.Repository.ProyectoRepository;
import com.Proyecto.WebClient.WebClientC1;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class ProyectoService {
    

@Autowired
    private ProyectoRepository proyectoRepository;

    public List<Proyecto> getProyectos(){
        return proyectoRepository.findAll();
    }

    public Proyecto gProyectoPorId(Long Id){
        return proyectoRepository.findById(Id)
        .orElseThrow(()-> new RuntimeException("Proyecto no encontrado"));
    }


   

    @Autowired
    private WebClientC1 webClientC1; 
    public Proyecto saveProyecto(Proyecto nuevo) {
        if (nuevo.getComentarios() == null || nuevo.getComentarios().isBlank()) {
            throw new IllegalArgumentException("El campo 'comentarios' es obligatorio.");
        }
        if (nuevo.getFecha() == null) {
            throw new IllegalArgumentException("El campo 'Fecha' es obligatorio.");
        }
        if (nuevo.getId_contrato() == null) {
            throw new IllegalArgumentException("El campo 'Id_contrato' es obligatorio.");
        }
        if (nuevo.getId_tecnico() == null) {
            throw new IllegalArgumentException("El campo 'Id_tecnico' es obligatorio.");
        }
        if (nuevo.getId_estado() == null) {
            throw new IllegalArgumentException("El campo 'Id_estado' es obligatorio.");
        }

        Map<String, Object> contrato = webClientC1.getContratoById(nuevo.getId_contrato());
        if (contrato == null || contrato.isEmpty()) {
            throw new RuntimeException("Contrato no encontrado, no se puede crear el proyecto");
        }


        return proyectoRepository.save(nuevo);
    }
}


