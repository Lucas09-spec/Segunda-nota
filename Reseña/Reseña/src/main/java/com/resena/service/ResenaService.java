package com.resena.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.resena.model.Resena;
import com.resena.repository.ResenaRepository;
import com.resena.WebClient.UserClient;
import com.resena.WebClient.WebServicio;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private WebServicio webServicio;

    @Autowired
    private UserClient webUser;

    public List<Resena> getResenas() {
        return resenaRepository.findAll();
    }

    public Resena getResenaPorId(Long id) {
        return resenaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada con ID: " + id));
    }

    public Resena saveResena(Resena nuevo) {
        if (nuevo == null) {
            throw new IllegalArgumentException("La reseña no puede ser null");
        }

        if (!StringUtils.hasText(nuevo.getComentario())) {
            throw new IllegalArgumentException("El comentario es obligatorio");
        }

        if (nuevo.getF_com() == null) {
            throw new IllegalArgumentException("La fecha del comentario es obligatoria");
        }

        if (nuevo.getId_Usuario() == null) {
            throw new IllegalArgumentException("El ID de usuario es obligatorio");
        }

        if (nuevo.getId_servicio() == null) {
            throw new IllegalArgumentException("El ID del servicio es obligatorio");
        }

        if (nuevo.getNota() == null || nuevo.getNota() < 1 || nuevo.getNota() > 5) {
            throw new IllegalArgumentException("La nota debe ser un valor entre 1 y 5");
        }

        try {
            Map<String, Object> servicio = webServicio.getServicioById(nuevo.getId_servicio());
            if (servicio == null || servicio.isEmpty()) {
                throw new IllegalArgumentException("El servicio indicado no existe");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al validar el servicio: " + e.getMessage());
        }

        try {
            Map<String, Object> usuario = webUser.getUserById(nuevo.getId_Usuario());
            if (usuario == null || usuario.isEmpty()) {
                throw new IllegalArgumentException("El usuario indicado no existe");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al validar el usuario: " + e.getMessage());
        }

        return resenaRepository.save(nuevo);
    }
}
