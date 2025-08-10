package com.ciber.rrhhservice.presentation.organizacion.dto;

import java.math.BigDecimal;

public record DepartamentoResponseDto(
        Long departamentoId,
        String nombre,
        String descripcion,
        String codigo,
        BigDecimal presupuesto
) {
}
