package com.ciber.rrhhservice.application.empleado.usecase;

import com.ciber.rrhhservice.domain.empleado.model.EmpleadoModel;
import com.ciber.rrhhservice.domain.empleado.repository.EmpleadoRepository;
import com.ciber.rrhhservice.domain.empleado.valueobject.PaginaResult;
import com.ciber.rrhhservice.domain.empleado.valueobject.PaginacionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListarEmpleadoUseCase {

    private final EmpleadoRepository empleadoRepository;

    public PaginaResult<EmpleadoModel> ejecutar(PaginacionRequest paginacion) {
        return empleadoRepository.listarEmpleados(paginacion);
    }

}
