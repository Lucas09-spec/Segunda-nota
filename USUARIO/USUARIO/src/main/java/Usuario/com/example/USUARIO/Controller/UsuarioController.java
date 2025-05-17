package Usuario.com.example.USUARIO.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import Usuario.com.example.USUARIO.Model.Usuario;
import Usuario.com.example.USUARIO.Service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService; 

    @PostMapping 
    public ResponseEntity<Usuario> guardarusuario(@RequestBody Usuario usuario){
        System.out.println("Usuario recibido: " + usuario);
        return ResponseEntity.status(201).body(usuarioService.guardarusuario(usuario));
    }
@GetMapping
public ResponseEntity<List<Usuario>> obtenerusuarios(){
    List <Usuario> lista =  usuarioService.obteneruUsuarios();
    if (lista.isEmpty()){
        return ResponseEntity.noContent().build();

    }return ResponseEntity.ok(lista);
}


}
 