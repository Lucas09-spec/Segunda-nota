package Servicio.com.example.Servicio.Model;

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
@Data
@Table(name = "Servicios")
@NoArgsConstructor
@AllArgsConstructor

public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id; 

     @Column(unique = true,nullable = false , name = "Descripcion del servicio")
     private String descripcion;

     @Column (nullable = false , name = "Precio del servicio")
     private Long precio; 

     @Column(nullable = false , name = "Disponibilidad")

     private boolean disp;


}
