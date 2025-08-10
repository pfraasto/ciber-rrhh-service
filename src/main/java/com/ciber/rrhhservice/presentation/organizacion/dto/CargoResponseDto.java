package com.ciber.rrhhservice.presentation.organizacion.dto;

import java.math.BigDecimal;

public record CargoResponseDto(
        Long id,
        String nombre,
        String descripcion,
        Integer nivelJerarquico,
        BigDecimal salarioBase
) {
}
