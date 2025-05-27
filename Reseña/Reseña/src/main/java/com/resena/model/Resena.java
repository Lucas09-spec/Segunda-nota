package com.resena.model;

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

@Table (name = "usuario")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Resena {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)    
    private Long Id_Reseña;
    @Column (nullable = false)
    private Long Id_Usuario;
    @Column (nullable = false)
    private Long Id_servicio;
    
    @Column (nullable = false)
    private String comentario;

    @Column (nullable = false)
    private Date f_com;

    @Column (nullable = false)
    private Integer Nota;


    
}
