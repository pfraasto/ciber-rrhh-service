package com.ciber.rrhhservice.application.seguridad.usecase;

import com.ciber.rrhhservice.domain.seguridad.exception.CredencialesInvalidasException;
import com.ciber.rrhhservice.domain.seguridad.model.SeguridadModel;
import com.ciber.rrhhservice.domain.seguridad.model.UsuarioModel;
import com.ciber.rrhhservice.domain.seguridad.service.TokenService;
import com.ciber.rrhhservice.infrastructure.configuration.seguridad.CustomUserDetails;
import com.ciber.rrhhservice.infrastructure.persistence.seguridad.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class AutenticarUsuarioUseCase {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final CustomUserDetailsService userDetailsService;

    public SeguridadModel ejecutar(String username, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (Exception ex) {
            log.warn("Autenticación fallida para el usuario '{}'", username, ex);
            throw new CredencialesInvalidasException("Credenciales inválidas");
        }

        return generarTokensDesdeUsername(username);
    }

    private SeguridadModel generarTokensDesdeUsername(String username) {
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
        UsuarioModel usuario = userDetails.getUsuario();

        String accessToken = tokenService.generarTokenAcceso(usuario);
        String refreshToken = tokenService.generarTokenRefresco(usuario);

        return SeguridadModel.builder()
                .token(accessToken)
                .refresh(refreshToken)
                .build();
    }
}
