package com.ciber.rrhhservice.infrastructure.persistence.empleado.entity;

import com.ciber.rrhhservice.domain.empleado.enums.EstadoCivil;
import com.ciber.rrhhservice.domain.empleado.enums.EstadoEmpleado;
import com.ciber.rrhhservice.domain.empleado.enums.Genero;
import com.ciber.rrhhservice.domain.empleado.enums.TipoDocumento;
import com.ciber.rrhhservice.infrastructure.persistence.shared.Auditoria;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "empleados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpleadoEntity extends Auditoria<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_empleado", length = 20, nullable = false)
    private String numeroEmpleado;

    @Column(length = 100, nullable = false)
    private String nombres;

    @Column(length = 100, nullable = false)
    private String apellidos;

    @Column(name = "documento_identidad", length = 20, nullable = false)
    private String documentoIdentidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento", nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column
    private Genero genero;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil")
    private EstadoCivil estadoCivil;

    @Column(length = 20)
    private String telefono;

    @Column(name = "email_personal", length = 100)
    private String emailPersonal;

    @Column(name = "email_corporativo", length = 100)
    private String emailCorporativo;

    @Column(columnDefinition = "TEXT")
    private String direccion;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;

    @Column(name = "fecha_salida")
    private LocalDate fechaSalida;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoEmpleado estado;

    @Column(name = "cargo_id")
    private Long cargoId;

    @Column(name = "departamento_id")
    private Long departamentoId;

    @Column(name = "supervisor_id")
    private Long supervisorId;
}
