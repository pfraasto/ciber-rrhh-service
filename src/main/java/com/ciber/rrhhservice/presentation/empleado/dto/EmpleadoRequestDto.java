package com.ciber.rrhhservice.presentation.empleado.dto;

import com.ciber.rrhhservice.domain.empleado.enums.EstadoCivil;
import com.ciber.rrhhservice.domain.empleado.enums.Genero;
import com.ciber.rrhhservice.domain.empleado.enums.TipoDocumento;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record EmpleadoRequestDto(

        @NotBlank(message = "Los nombres son obligatorios")
        @Size(max = 100, message = "Los nombres no pueden exceder 100 caracteres")
        String nombres,

        @NotBlank(message = "Los apellidos son obligatorios")
        @Size(max = 100, message = "Los apellidos no pueden exceder 100 caracteres")
        String apellidos,

        @NotBlank(message = "El documento es obligatorio")
        @Size(max = 20, message = "El documento no puede exceder 20 caracteres")
        String documento,

        @NotNull(message = "El tipo de documento es obligatorio")
        TipoDocumento tipoDocumento,

        @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
        LocalDate fechaNacimiento,

        Genero genero,

        EstadoCivil estadoCivil,

        @Size(max = 20, message = "El teléfono no puede exceder 20 caracteres")
        String telefono,

        @Email(message = "El email personal no tiene un formato válido")
        @Size(max = 100, message = "El email personal no puede exceder 100 caracteres")
        String emailPersonal,

        @Email(message = "El email corporativo no tiene un formato válido")
        @Size(max = 100, message = "El email corporativo no puede exceder 100 caracteres")
        String emailCorporativo,

        String direccion,

        @NotNull(message = "La fecha de ingreso es obligatoria")
        LocalDate fechaIngreso,

        Long cargoId,

        Long departamentoId,

        Long supervisorId
) {
}
