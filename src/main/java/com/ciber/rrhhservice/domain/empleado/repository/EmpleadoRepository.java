package com.ciber.rrhhservice.domain.empleado.repository;

import com.ciber.rrhhservice.domain.empleado.model.EmpleadoModel;
import com.ciber.rrhhservice.domain.empleado.valueobject.PaginaResult;
import com.ciber.rrhhservice.domain.empleado.valueobject.PaginacionRequest;

public interface EmpleadoRepository {
    EmpleadoModel guardarEmpleado(EmpleadoModel empleado);

    boolean existeEmpleado(String numeroDocumento);

    Integer ultimoNumeroEmpleado();

    PaginaResult<EmpleadoModel> listarEmpleados(PaginacionRequest paginacion);

}
