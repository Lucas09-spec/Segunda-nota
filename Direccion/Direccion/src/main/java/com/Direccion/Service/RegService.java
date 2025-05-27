package com.Direccion.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Direccion.Model.Direccion;
import com.Direccion.Model.Region;
import com.Direccion.Repository.DireccionRepository;
import com.Direccion.Repository.RegRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegService {
    @Autowired 
    
    private RegRepository regRepository;

    public List<Region> getRegions(){
        return regRepository.findAll();
    }

    public Region getRegionPorId(Long Id){
        return regRepository.findById(Id)
        .orElseThrow(()-> new RuntimeException("Region no encontrada"));
        }


    public Region saveRegion (Region nuevo){
        if (nuevo.getId_reg() == null || nuevo.getNom_reg() == null){
            throw new IllegalArgumentException("Todos los campos son obligatorios");
        }
        return regRepository.save(nuevo);
    }
    
}




