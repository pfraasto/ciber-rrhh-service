package com.ciber.rrhhservice.domain.usuario.valueobject;

import lombok.Value;

@Value
public class Username {
    private final String value;

    public Username(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío.");
        }
        if (value.length() > 50) {
            throw new IllegalArgumentException("El nombre de usuario no puede exceder 50 caracteres.");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
