package com.example.Contratacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.Contratacion.model.ContratacionModel;

@Service
public interface ContratacionRepository extends JpaRepository<ContratacionModel, Long>{

}
