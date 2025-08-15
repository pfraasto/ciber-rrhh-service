package com.ciber.rrhhservice.domain.empleado.model;

import com.ciber.rrhhservice.domain.empleado.enums.EstadoCivil;
import com.ciber.rrhhservice.domain.empleado.enums.EstadoEmpleado;
import com.ciber.rrhhservice.domain.empleado.enums.Genero;
import com.ciber.rrhhservice.domain.empleado.enums.TipoDocumento;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpleadoModel {
    private Long empleadoId;
    private String numero;
    private String nombres;
    private String apellidos;
    private String documento;
    private TipoDocumento tipoDocumento;
    private LocalDate fechaNacimiento;
    private Genero genero;
    private EstadoCivil estadoCivil;
    private String telefono;
    private String emailPersonal;
    private String emailCorporativo;
    private String direccion;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private EstadoEmpleado estado;
    private Long cargoId;
    private String cargo;
    private Long departamentoId;
    private String departamento;
    private Long supervisorId;
    private String supervisor;
}
