package Servicio.com.example.Servicio.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Servicio.com.example.Servicio.Model.Servicio;
import Servicio.com.example.Servicio.Service.ServicioService;

@RestController
@RequestMapping("/api/v1/Servicio")
public class ServicioController {
    @Autowired 
    private ServicioService servicioService;

    @GetMapping
    public ResponseEntity<List<Servicio>> obtenerServicio(){
        List<Servicio> lista = servicioService.ObtenerLista(); 
        if (lista.isEmpty()){
            return ResponseEntity.noContent().build();
        } return ResponseEntity.ok(lista);


    }

    @PostMapping
    public ResponseEntity<Servicio> guardarServicio(@RequestBody Servicio nuevo){
        return ResponseEntity.status(201).body(servicioService.guardarServicio(nuevo));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Servicio> eliminarServicio(@PathVariable Long id) {
    boolean eliminado = servicioService.eliminarServicio(id);
    if (eliminado) {
        return ResponseEntity.noContent().build(); 
    } else {
        return ResponseEntity.notFound().build(); 
    }
}

}
