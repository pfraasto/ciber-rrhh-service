package com.ciber.rrhhservice.infrastructure.persistence.usuario.entity;

import com.ciber.rrhhservice.shared.Auditoria;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioEntity extends Auditoria<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    private String nombre;

    private String apellido;

    private Boolean activo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioRolEntity> roles;
}
