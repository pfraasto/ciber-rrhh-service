package com.ciber.rrhhservice.domain.usuario.valueobject;

import lombok.Value;

@Value
public class Email {
    private final String value;

    public Email(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El correo no puede estar vacío.");
        }
        if (value.length() > 320 || !value.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Formato de correo inválido.");
        }
        if (value.length() > 100) {
            throw new IllegalArgumentException("El correo no puede exceder 100 caracteres.");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

