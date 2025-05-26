package com.example.Soporte.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Soporte.model.Soporte;
import com.example.Soporte.repository.SoporteRepository;
import com.example.Soporte.webclient.CategoriaClient;

@Service
public class SoporteService {
    @Autowired
    private SoporteRepository soporteRepository;
    @Autowired
    private CategoriaClient categoriaClient;


    

    public List<Soporte> findAll(){
        return soporteRepository.findAll();
    }

    public Optional<Soporte> findById(Long id){
        return  soporteRepository.findById(id);
    }

    public Soporte savePedido(Soporte nuevoSoporte){
        //verificar si el cliente existe consultando al microservicio cliente
        Map<String,Object> categoria = categoriaClient.getCategoriaById(nuevoSoporte.getCategoriaId());
        //verifico si me trajo el cliente o no
        if(categoria == null || categoria.isEmpty()){
            throw new RuntimeException("Cliente no encontrado. No se puede agregar el pedido");
        }
        return soporteRepository.save(nuevoSoporte);

    }

    public void delete(Long id) {
        soporteRepository.deleteById(id);
    }

}
