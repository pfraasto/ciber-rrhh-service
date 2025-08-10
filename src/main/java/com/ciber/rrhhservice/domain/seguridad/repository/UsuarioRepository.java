package com.ciber.rrhhservice.domain.seguridad.repository;

import com.ciber.rrhhservice.domain.seguridad.model.UsuarioModel;

import java.util.Optional;


public interface UsuarioRepository {
    Optional<UsuarioModel> usuarioPorUserName(String username);

    void guardarToken(String token);

    String obtenerTokenCache(String username);
}
