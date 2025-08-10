package com.ciber.rrhhservice.domain.organizacion.model;

import com.ciber.rrhhservice.domain.shared.enums.Estado;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CargoModel {
    private Long cargoId;
    private String nombre;
    private String descripcion;
    private Integer nivelJerarquico;
    private BigDecimal salarioBase;
    private Long departamentoId;
    private Estado estado;
}
