package com.ciber.rrhhservice.application.empleado.usecase;

import com.ciber.rrhhservice.domain.empleado.enums.EstadoEmpleado;
import com.ciber.rrhhservice.domain.empleado.model.EmpleadoModel;
import com.ciber.rrhhservice.domain.empleado.repository.EmpleadoRepository;
import com.ciber.rrhhservice.infrastructure.shared.constant.ErrorConstant;
import com.error.springerrorhandler.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrarEmpleadoUseCase {

    private final EmpleadoRepository empleadoRepository;
    private static final String EMPLEADO_PREFIX = "EMP";
    private static final int NUMERO_DIGITOS = 3;

    public EmpleadoModel registrarEmpleado(EmpleadoModel empleado) {

        validarDocumentoUnico(empleado.getDocumento());

        String nuevoNumeroEmpleado = generarNumeroEmpleado();

        empleado.setEstado(EstadoEmpleado.ACTIVO);
        empleado.setNumero(nuevoNumeroEmpleado);

        return empleadoRepository.guardarEmpleado(empleado);
    }

    private void validarDocumentoUnico(String documento) {
        if (empleadoRepository.existeEmpleado(documento)) {
            throw new BusinessException(ErrorConstant.ERROR_EMPLEADO_DOCUMENTO_EXIST);
        }
    }

    private String generarNumeroEmpleado() {
        Integer ultimoNumero = empleadoRepository.ultimoNumeroEmpleado();
        int siguienteNumero = (ultimoNumero == null ? 1 : ultimoNumero + 1);
        return EMPLEADO_PREFIX + String.format("%0" + NUMERO_DIGITOS + "d", siguienteNumero);
    }

}
