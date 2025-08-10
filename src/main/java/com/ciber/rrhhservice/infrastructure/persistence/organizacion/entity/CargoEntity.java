package com.ciber.rrhhservice.infrastructure.persistence.organizacion.entity;

import com.ciber.rrhhservice.infrastructure.persistence.shared.Auditoria;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cargos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CargoEntity extends Auditoria<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    @Column(name = "nivel_jerarquico")
    private Integer nivelJerarquico;
    @Column(name = "salario_base")
    private BigDecimal salarioBase;
    @Column(name = "departamento_id")
    private Long departamentoId;
    private boolean activo;
}
