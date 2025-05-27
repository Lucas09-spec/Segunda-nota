package com.Proyecto.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Proyecto.Model.Proyecto;
import com.Proyecto.Repository.ProyectoRepository;
import com.Proyecto.WebClient.EstadoClient;
import com.Proyecto.WebClient.TecnicoClient;
import com.Proyecto.WebClient.WebClientC1;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private WebClientC1 webClientC1;

    @Autowired
    private TecnicoClient tecnicoClient;

    @Autowired
    private EstadoClient estadoClient;

    public List<Proyecto> getProyectos() {
        return proyectoRepository.findAll();
    }

    public Optional<Proyecto> getProyectoById(Long id) {
        return proyectoRepository.findById(id);
    }

    public Proyecto saveProyecto(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    public void deleteProyecto(Long id) {
        proyectoRepository.deleteById(id);
    }
} 
