package com.ciber.rrhhservice.application.seguridad;

import com.ciber.rrhhservice.application.seguridad.usecase.AutenticarUsuarioUseCase;
import com.ciber.rrhhservice.application.seguridad.usecase.RefrescarTokenUseCase;
import com.ciber.rrhhservice.domain.seguridad.model.SeguridadModel;
import com.ciber.rrhhservice.domain.seguridad.service.SeguridadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeguridadServiceImpl implements SeguridadService {

    private final AutenticarUsuarioUseCase autenticarUsuarioUseCase;
    private final RefrescarTokenUseCase refrescarTokenUseCase;

    @Override
    public SeguridadModel autenticacion(String username, String password) {
        return autenticarUsuarioUseCase.ejecutar(username, password);
    }

    @Override
    public SeguridadModel refrescar(String refreshToken) {
        return refrescarTokenUseCase.ejecutar(refreshToken);
    }
}
