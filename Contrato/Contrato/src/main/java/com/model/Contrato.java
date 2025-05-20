package com.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table (name = "usuario")

public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private Date fecha_contrato;
          
    //@Column(nullable = false)

    //private  Long id_usuario;

      //@Column(nullable = false)

    //private  Long id_direcc

        @Column(nullable = false)

    private Integer total;

     //@Column(nullable = false)

    //private  Long id_servicio
            @Column(nullable = false)

    private Date fecha_inicio;
            @Column(nullable = false)

    private Date fecha_final;





}
