package com.ciber.rrhhservice.domain.organizacion.model;

import com.ciber.rrhhservice.domain.shared.enums.Estado;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DepartamentoModel {
    private Long departamentoId;
    private String nombre;
    private String descripcion;
    private String codigo;
    private BigDecimal presupuesto;
    private Estado estado;
}
