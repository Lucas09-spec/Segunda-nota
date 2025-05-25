package com.example.Respuesta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Respuesta.model.Respuesta;
import com.example.Respuesta.repository.RespuestaRepository;

@Service
public class RespuestaService {
    private final RespuestaRepository respuestaRepository;


    @Autowired
    public RespuestaService(RespuestaRepository respuestaRepository) {
        this.respuestaRepository = respuestaRepository;
    }

    public List<Respuesta> findAll() {
        return respuestaRepository.findAll();
    }

    public Optional<Respuesta> findById(Long id) {
        return respuestaRepository.findById(id);
    }

    public Respuesta create(Respuesta respuesta) {
        return respuestaRepository.save(respuesta);
    }

    public Respuesta update(Long id, Respuesta respuesta) {
        return respuestaRepository.findById(id).map(existingRespuesta -> {
            existingRespuesta.setContenido(respuesta.getContenido());
            existingRespuesta.setSoporte(respuesta.getSoporte());
            return respuestaRepository.save(existingRespuesta);
        }).orElseThrow(() -> new RuntimeException("Respuesta no encontrada con id: " + id));
    }

    public void delete(Long id) {
        respuestaRepository.deleteById(id);
    }
}
