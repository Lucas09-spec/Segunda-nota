package com.example.Soporte.model;

import java.time.LocalDate;

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
@Table(name = "soporte")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Soporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSoporte;

    @Column (nullable=false)
    private LocalDate fechaReal;

    @Column (nullable= false)
    private Long idUsuario;

    @Column (nullable= false)
    private Long idCategoria;

    @Column (nullable= false)
    private String descripcion;

    @Column (nullable= false)
    private Long idEstado;
}
