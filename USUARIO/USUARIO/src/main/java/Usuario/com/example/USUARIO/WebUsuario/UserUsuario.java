package Usuario.com.example.USUARIO.WebUsuario;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;

@Component
public class UserUsuario {
    private final WebClient webClient; 
   

 public UserUsuario(@Value("${Roles-service.url}") String UsuarioServiceUrl){
        this.webClient = WebClient.builder().baseUrl(UsuarioServiceUrl).build();
    }
    public String getAllRoles() {
        return this.webClient.get()
                             .retrieve()
                             .bodyToMono(String.class)
                             .block();
    }
}
