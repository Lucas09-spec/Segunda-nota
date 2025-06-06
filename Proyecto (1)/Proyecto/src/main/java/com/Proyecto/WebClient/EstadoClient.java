package com.Proyecto.WebClient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class EstadoClient {

    private final WebClient webClient;

    public EstadoClient(@Value("${estado-service.url}") String estadoServiceUrl) {
        this.webClient = WebClient.builder().baseUrl(estadoServiceUrl).build();
    }

    public Map<String, Object> getEstadoById(Long id) {
        return this.webClient.get()
            .uri("/{id}", id)
            .retrieve()
            .onStatus(
                status -> status.is4xxClientError(),
                response -> response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new RuntimeException("Estado no encontrado")))
            )
            .bodyToMono(Map.class)
            .block();
    }
}
