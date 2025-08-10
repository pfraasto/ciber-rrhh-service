package com.ciber.rrhhservice.domain.seguridad.service;


import com.ciber.rrhhservice.domain.seguridad.model.SeguridadModel;

public interface SeguridadService {
    SeguridadModel autenticacion(String username, String password);

    SeguridadModel refrescar(String token);
}
