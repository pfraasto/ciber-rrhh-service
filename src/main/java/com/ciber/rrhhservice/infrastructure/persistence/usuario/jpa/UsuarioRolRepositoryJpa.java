package com.ciber.rrhhservice.infrastructure.persistence.usuario.jpa;

import com.ciber.rrhhservice.infrastructure.persistence.usuario.entity.RolEntity;
import com.ciber.rrhhservice.infrastructure.persistence.usuario.entity.UsuarioEntity;
import com.ciber.rrhhservice.infrastructure.persistence.usuario.entity.UsuarioRolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRolRepositoryJpa extends JpaRepository<UsuarioRolEntity, Long> {
    List<UsuarioRolEntity> findByUsuario(UsuarioEntity usuario);

    List<UsuarioRolEntity> findByRol(RolEntity rol);

    Optional<UsuarioRolEntity> findByUsuarioAndRol(UsuarioEntity usuario, RolEntity rol);
}
