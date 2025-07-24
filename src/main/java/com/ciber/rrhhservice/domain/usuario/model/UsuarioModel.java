package com.ciber.rrhhservice.domain.usuario.model;

import com.ciber.rrhhservice.domain.usuario.valueobject.Email;
import com.ciber.rrhhservice.domain.usuario.valueobject.Username;
import lombok.Data;

import java.util.Set;

@Data
public class UsuarioModel {
    private Long id;
    private Username username;
    private Email email;
    private String passwordHash;
    private String nombre;
    private String apellido;
    private String estado;
    private Set<UsuarioRolModel> roles;
}
