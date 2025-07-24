package com.rrhh.ciberrrhhservice.infrastructure.persistence.usuario.jpa;

import com.rrhh.ciberrrhhservice.infrastructure.persistence.usuario.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositoryJpa extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByUsername(String username);

    Optional<UsuarioEntity> findByEmail(String email);

    @Query("""
                SELECT u FROM UsuarioEntity u
                LEFT JOIN FETCH u.roles ur
                LEFT JOIN FETCH ur.rol
                WHERE u.activo = :activo
            """)
    List<UsuarioEntity> usuariosConRoles(@Param("activo") boolean activo);
}
