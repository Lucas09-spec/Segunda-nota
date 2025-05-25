package com.example.Soporte.service;

import java.lang.foreign.Linker;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Soporte.model.Soporte;
import com.example.Soporte.repository.SoporteRepository;

@Service
public class SoporteService {
    private final SoporteRepository soporteRepository;

    @Autowired
    public SoporteService(SoporteRepository soporteRepository){
        this.soporteRepository = soporteRepository;
    }

    public List<Soporte> findAll(){
        return soporteRepository.findAll();
    }

    public Optional<Soporte> findById(Long id){
        return  soporteRepository.findById(id);
    }

    public Soporte create(Soporte soporte) {
        return soporteRepository.save(soporte);
    }

    public Soporte update(Long id, Soporte soporte) {
        return soporteRepository.findById(id).map(existingSoporte -> {
            existingSoporte.setTitulo(soporte.getTitulo());
            existingSoporte.setDescripcion(soporte.getDescripcion());
            existingSoporte.setFechaInicio(soporte.getFechaInicio());
            existingSoporte.setCategoria(soporte.getCategoria());
            // Se pueden actualizar otros campos o relaciones si es necesario.
            return soporteRepository.save(existingSoporte);
        }).orElseThrow(() -> new RuntimeException("Soporte no encontrado con id: " + id));
    }

    public void delete(Long id) {
        soporteRepository.deleteById(id);
    }

}
