package com.example.Respuesta.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Respuesta.model.Respuesta;
import com.example.Respuesta.repository.RespuestaRepository;
import com.example.Respuesta.webclient.SoporteClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RespuestaService {
    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private SoporteClient soporteClient;

    public List<Respuesta> getRespuestas(){
        return respuestaRepository.findAll();
    }

    public Respuesta saveRespuesta(Respuesta nuevoRespuesta){
        Map<String,Object> soporte = soporteClient.getSoporteById(nuevoRespuesta.getSoporteId());
        if (soporte == null || soporte.isEmpty()){
            throw new RuntimeException("Soporte no encontrado.");
        }
        return respuestaRepository.save(nuevoRespuesta);
    }
    public void delete(Long id) {
        respuestaRepository.deleteById(id);
    }

}
