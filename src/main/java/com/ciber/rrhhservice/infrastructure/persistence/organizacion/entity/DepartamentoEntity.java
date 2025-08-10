package com.ciber.rrhhservice.infrastructure.persistence.organizacion.entity;

import com.ciber.rrhhservice.infrastructure.persistence.shared.Auditoria;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "departamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartamentoEntity extends Auditoria<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String codigo;
    private BigDecimal presupuesto;
    private boolean activo;
}
