package com.rrhh.ciberrrhhservice.infrastructure.configuration;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareConfig implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        //TODO: Aquí deberías devolver el usuario logueado (Spring Security, etc.)
        return Optional.of("admin");
    }

}