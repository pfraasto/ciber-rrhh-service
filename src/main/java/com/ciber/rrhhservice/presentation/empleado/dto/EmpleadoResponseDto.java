package com.ciber.rrhhservice.presentation.empleado.dto;


import java.time.LocalDate;

public record EmpleadoResponseDto(
        Long empleadoId,
        String numero,
        String nombres,
        String apellidos,
        String documento,
        String tipoDocumento,
        LocalDate fechaNacimiento,
        String genero,
        String estadoCivil,
        String telefono,
        String emailPersonal,
        String emailCorporativo,
        String direccion,
        LocalDate fechaIngreso,
        LocalDate fechaSalida,
        String estado,
        Long cargoId,
        String cargo,
        Long departamentoId,
        String departamento,
        Long supervisorId,
        String supervisor
) {
}
