package com.rrhh.ciberrrhhservice.infrastructure.persistence.usuario.entity;

import com.rrhh.ciberrrhhservice.shared.Auditoria;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolEntity extends Auditoria<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private Boolean activo;
}
