package com.ciber.rrhhservice.domain.seguridad.service;


import com.ciber.rrhhservice.domain.seguridad.model.UsuarioModel;
import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {
    String generarTokenAcceso(UsuarioModel usuario);

    String generarTokenRefresco(UsuarioModel usuario);

    String extraerUsuario(String token);

    boolean esTokenValido(String token);

    UserDetails crearUserDetailsDesdeToken(String token);
}
