package com.ciber.rrhhservice.domain.usuario.repository;

import com.ciber.rrhhservice.domain.usuario.model.UsuarioModel;
import com.ciber.rrhhservice.domain.usuario.model.UsuarioRolModel;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    List<UsuarioModel> findAllUsuarios();

    Optional<UsuarioModel> findUsuarioById(Long id);

    Optional<UsuarioModel> findUsuarioByUsername(String username);

    UsuarioModel saveUsuario(UsuarioModel usuario);

    void deleteUsuarioById(Long id);

    Optional<UsuarioRolModel> findRolById(Long id);
}
