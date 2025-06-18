package Rol.com.example.ROL.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Rol.com.example.ROL.Model.RolModel;
import Rol.com.example.ROL.Repository.RolRepository;
import Rol.com.example.ROL.Service.RolService;

@RestController
@RequestMapping("/api/v1/Roles")
public class RolController {
    @Autowired
    private RolService rolService; 

    @PostMapping 
    public ResponseEntity<RolModel> guardarrol(@RequestBody RolModel nuevoRol ){
        return ResponseEntity.status(201).body(rolService.guardarRol(nuevoRol));

    }

    @GetMapping
    public ResponseEntity<List<RolModel>> obtenerRoles(){
        List<RolModel> lista = rolService.getRol();
        if (lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{/id}")
    public ResponseEntity<RolModel> obtenerRolPorId(@PathVariable Long id){
        RolModel rol = rolService.obtenerRolPorId(id);
        if (rol == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rol);
    }


}
