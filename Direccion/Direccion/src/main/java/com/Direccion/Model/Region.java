package com.Direccion.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "Usuario")




public class Region {

       @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long Id_reg;

    @Column (nullable = false)
    private String Nom_reg;




}
