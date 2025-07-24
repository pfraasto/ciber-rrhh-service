package com.ciber.rrhhservice.presentation;

import com.ciber.rrhhservice.domain.usuario.model.UsuarioModel;
import com.ciber.rrhhservice.domain.usuario.model.UsuarioRolModel;
import com.ciber.rrhhservice.domain.usuario.service.UsuarioService;
import com.ciber.rrhhservice.domain.usuario.valueobject.Email;
import com.ciber.rrhhservice.domain.usuario.valueobject.Username;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class Init {

    private final UsuarioService usuarioService;

    @PostConstruct
    public void prueba() {

        //Listar todos los usuarios con sus roles
        List<UsuarioModel> usuarios = usuarioService.listarUsuarios();
        usuarios.forEach(usuario -> {
            log.info("Usuario: {}", usuario.getUsername());
            usuario.getRoles().forEach(role -> log.info("Role: " + role.getNombre()));
        });

        //Buscar usuario por ID
        Optional<UsuarioModel> usuario = usuarioService.buscarPorId(1L);
        usuario.ifPresent(usuarioModel -> log.info("Usuario por id: {}", usuarioModel.getUsername()));

        //Buscar usuario por username
        Optional<UsuarioModel> usuarioUsername = usuarioService.buscarPorUsername("admin");
        usuario.ifPresent(usuarioModel -> log.info("Usuario por Username: {}", usuarioModel.getUsername()));

        UsuarioRolModel usuarioRolModel = new UsuarioRolModel();
        usuarioRolModel.setId(1L);
        usuarioRolModel.setNombre("EMPLEADO");
        usuarioRolModel.setDescripcion("Empleado estándar");

        //Guardar nuevo usuario con rol
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setUsername(new Username("prueba 2"));
        usuarioModel.setEmail(new Email("prueba2@gmail.com"));
        usuarioModel.setPasswordHash("abc*****");
        usuarioModel.setNombre("Prueba de usuario");
        usuarioModel.setApellido("Apellido de prueba");
        usuarioModel.setEstado("ACTIVO");
        usuarioModel.setRoles(Set.of(usuarioRolModel));

        UsuarioModel guardarUsuario = usuarioService.guardar(usuarioModel);
        log.info("Usuario guardado: id ={},nombre={}", guardarUsuario.getId(), guardarUsuario.getUsername());
    }

}
