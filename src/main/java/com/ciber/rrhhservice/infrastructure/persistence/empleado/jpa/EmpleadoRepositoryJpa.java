package com.ciber.rrhhservice.infrastructure.persistence.empleado.jpa;

import com.ciber.rrhhservice.infrastructure.persistence.empleado.entity.EmpleadoEntity;
import com.ciber.rrhhservice.infrastructure.persistence.empleado.projection.EmpleadoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmpleadoRepositoryJpa extends JpaRepository<EmpleadoEntity, Long> {

    Optional<EmpleadoEntity> findByDocumentoIdentidad(String documentoIdentidad);

    @Query(value = """
            SELECT CAST(SUBSTRING(numero_empleado, 4) AS UNSIGNED) AS numero
            FROM empleados
            ORDER BY numero DESC
            LIMIT 1;
            """, nativeQuery = true)
    Integer ultimoNumeroEmpleado();


    @Query(
            value = """
                     SELECT e.id as id,
                                 e.numeroEmpleado as numeroEmpleado,
                                 e.nombres as nombres,
                                 e.apellidos as apellidos,
                                 e.documentoIdentidad as documentoIdentidad,
                                 e.tipoDocumento as tipoDocumento,
                                 e.fechaNacimiento as fechaNacimiento,
                                 e.genero as genero,
                                 e.estadoCivil as estadoCivil,
                                 e.telefono as telefono,
                                 e.emailPersonal as emailPersonal,
                                 e.emailCorporativo as emailCorporativo,
                                 e.direccion as direccion,
                                 e.fechaIngreso as fechaIngreso,
                                 e.fechaSalida as fechaSalida,
                                 e.estado as estado,
                                 e.cargoId as cargoId,
                                 c.nombre as cargo,
                                 e.departamentoId as departamentoId,
                                 d.nombre as departamento
                          FROM EmpleadoEntity e
                          LEFT JOIN CargoEntity c ON e.cargoId = c.id
                          LEFT JOIN DepartamentoEntity d ON e.departamentoId = d.id
                    """
    )
    Page<EmpleadoProjection> findByAllEmpleados(Pageable pageable);
}
