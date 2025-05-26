package Estado.com.example.ESTADO.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Estado.com.example.ESTADO.Model.ESTADO;
import Estado.com.example.ESTADO.Service.ESTADOService;

@RequestMapping("/api/v1/estado")
@RestController
public class ESTADOController {

    @Autowired 
    private ESTADOService estadoService; 

    @GetMapping
    public ResponseEntity<List<ESTADO>> obtenerEstado() {
        List<ESTADO> lista = estadoService.obEstados();

        if (lista == null || lista.isEmpty()) {

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(lista);
    }
    
    @PostMapping

    public ResponseEntity<?> guardar(@RequestBody ESTADO nuevo) {
        try {
            ESTADO guardado = estadoService.guardarEstado(nuevo);
            return ResponseEntity.status(201).body(guardado);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno: " + e.getMessage());
        }
    }
}
