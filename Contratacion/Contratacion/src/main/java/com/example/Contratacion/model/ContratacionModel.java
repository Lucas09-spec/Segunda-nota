package com.example.Contratacion.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "contratacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContratacionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable=false)
    private int total;

    @Column (nullable=false)
    private Date fechaInicio;

    @Column (nullable=false)
    private Date fechaFinal;

    @Column (nullable=false)
    private Long usuarioId;

    @Column (nullable=false)
    private Long direccionId;

    @Column (nullable=false)
    private Long servicioId;


}
