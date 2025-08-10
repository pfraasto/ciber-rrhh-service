package com.ciber.rrhhservice.domain.seguridad.service;


import com.ciber.rrhhservice.domain.seguridad.model.UsuarioModel;

public interface TokenService {
    String generarTokenAcceso(UsuarioModel usuario);

    String generarTokenRefresco(UsuarioModel usuario);

    String extraerUsuario(String token);

    boolean esTokenValido(String token);
}
