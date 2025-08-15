package com.ciber.rrhhservice.domain.empleado.service;

import com.ciber.rrhhservice.domain.empleado.model.EmpleadoModel;
import com.ciber.rrhhservice.domain.empleado.valueobject.PaginaResult;
import com.ciber.rrhhservice.domain.empleado.valueobject.PaginacionRequest;

public interface EmpleadoService {
    EmpleadoModel registrarEmpleado(EmpleadoModel empleado);

    PaginaResult<EmpleadoModel> listarEmpleados(PaginacionRequest paginacion);
}
