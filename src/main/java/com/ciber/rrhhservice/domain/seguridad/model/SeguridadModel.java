package com.ciber.rrhhservice.domain.seguridad.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeguridadModel {
    private String token;
    private String refresh;
}
