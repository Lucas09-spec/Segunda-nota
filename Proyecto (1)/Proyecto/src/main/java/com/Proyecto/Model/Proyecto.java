package com.Proyecto.Model;

import java.sql.Date;

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
@Table(name = "Usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Proyecto {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long Id_proyecto;

    @Column(nullable = false)
    private Long Id_contrato;

    @Column (nullable = false)
    private Long Id_tecnico;

    @Column (nullable = false)
    private Date Fecha;

    @Column (nullable = false)
    private String comentarios;

    @Column (nullable = false)
    private Long Id_estado;

}
