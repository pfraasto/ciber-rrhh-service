package com.ciber.rrhhservice.application.usuario.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Estados {
    ACTIVO(true, "Registro activo"),
    INACTIVO(false, "Registro inactivo");

    private boolean valor;
    private String descripcion;

    public static Estados buscar(boolean valor) {
        return Arrays.stream(Estados.values())
                .filter(e -> e.valor == valor)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported type %s.", valor)));
    }

    public static boolean obtenerValor(Estados estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo");
        }
        return estado.isValor();
    }
}
