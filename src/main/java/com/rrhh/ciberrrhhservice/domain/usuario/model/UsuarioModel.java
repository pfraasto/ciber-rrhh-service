package com.rrhh.ciberrrhhservice.domain.usuario.model;

import com.rrhh.ciberrrhhservice.domain.usuario.valueobject.Email;
import com.rrhh.ciberrrhhservice.domain.usuario.valueobject.Username;
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
