package com.ciber.rrhhservice.infrastructure.persistence.empleado.projection;

import java.time.LocalDate;

public interface EmpleadoProjection {
    Long getId();

    String getNumeroEmpleado();

    String getNombres();

    String getApellidos();

    String getDocumentoIdentidad();

    String getTipoDocumento();

    LocalDate getFechaNacimiento();

    String getGenero();

    String getEstadoCivil();

    String getTelefono();

    String getEmailPersonal();

    String getEmailCorporativo();

    String getDireccion();

    LocalDate getFechaIngreso();

    LocalDate getFechaSalida();

    String getEstado();

    Long getCargoId();

    String getCargo();

    Long getDepartamentoId();

    String getDepartamento();
}
