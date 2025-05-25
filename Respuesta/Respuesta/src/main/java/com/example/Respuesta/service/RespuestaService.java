package com.example.Respuesta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.Respuesta.model.Respuesta;
import java.util.Optional;
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
}
