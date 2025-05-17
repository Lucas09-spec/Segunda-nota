package Usuario.com.example.USUARIO.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Usuario.com.example.USUARIO.Model.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario ,Long>  {

}
