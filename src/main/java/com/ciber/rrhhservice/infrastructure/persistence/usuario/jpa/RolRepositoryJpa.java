package com.ciber.rrhhservice.infrastructure.persistence.usuario.jpa;

import com.ciber.rrhhservice.infrastructure.persistence.usuario.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepositoryJpa extends JpaRepository<RolEntity, Long> {
    Optional<RolEntity> findByNombre(String nombre);
}
