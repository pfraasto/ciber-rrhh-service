package com.ciber.rrhhservice.application.empleado;

import com.ciber.rrhhservice.application.empleado.usecase.ListarEmpleadoUseCase;
import com.ciber.rrhhservice.application.empleado.usecase.RegistrarEmpleadoUseCase;
import com.ciber.rrhhservice.domain.empleado.model.EmpleadoModel;
import com.ciber.rrhhservice.domain.empleado.service.EmpleadoService;
import com.ciber.rrhhservice.domain.empleado.valueobject.PaginaResult;
import com.ciber.rrhhservice.domain.empleado.valueobject.PaginacionRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

    private final RegistrarEmpleadoUseCase registrarEmpleadoUseCase;
    private final ListarEmpleadoUseCase listarEmpleadoUseCase;

    @Override
    @Transactional
    public EmpleadoModel registrarEmpleado(EmpleadoModel empleado) {
        return registrarEmpleadoUseCase.registrarEmpleado(empleado);
    }

    @Override
    public PaginaResult<EmpleadoModel> listarEmpleados(PaginacionRequest paginacion) {
        return listarEmpleadoUseCase.ejecutar(paginacion);
    }
}
