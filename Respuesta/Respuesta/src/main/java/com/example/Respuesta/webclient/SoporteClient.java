package com.example.Respuesta.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SoporteClient {
    private final WebClient webClient;


    public SoporteClient(@Value("${soporte*service.url}") String soporteServiceUrl){
        this.webClient = WebClient.builder().baseUrl(soporteServiceUrl).build();
    }
}
