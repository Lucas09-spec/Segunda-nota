package com.example.Contratacion.webusuario;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UsuarioUsuar {

    private final WebClient webUsuar;

    public UsuarioUsuar(@Value("${usuario-service.url}") String usuarioServiceUrl) {
        this.webUsuar = WebClient.builder().baseUrl(usuarioServiceUrl).build();
    }

    public Map<String, Object> getClienteById(Long usuarioId) {
        return this.webUsuar.get()
            .uri("/{id}", usuarioId)
            .retrieve()
            .onStatus(status -> status.is4xxClientError(),
                response -> response.bodyToMono(String.class)
                    .map(body -> new RuntimeException("Usuario no encontrado")))
            .bodyToMono(Map.class)
            .block();
    }
}
