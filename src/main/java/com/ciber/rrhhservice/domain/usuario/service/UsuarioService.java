package com.ciber.rrhhservice.domain.usuario.service;

import com.ciber.rrhhservice.domain.usuario.model.UsuarioModel;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<UsuarioModel> listarUsuarios();

    Optional<UsuarioModel> buscarPorId(Long id);

    Optional<UsuarioModel> buscarPorUsername(String username);

    UsuarioModel guardar(UsuarioModel usuario);

    void eliminar(Long id);
}
