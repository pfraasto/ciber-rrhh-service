package com.ciber.rrhhservice.infrastructure.persistence.seguridad.service;

import com.ciber.rrhhservice.domain.seguridad.model.RolModel;
import com.ciber.rrhhservice.domain.seguridad.model.UsuarioModel;
import com.ciber.rrhhservice.domain.seguridad.service.TokenService;
import com.ciber.rrhhservice.infrastructure.configuration.seguridad.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements TokenService {

    private final JwtProperties jwtProperties;

    /**
     * Genera un Access Token con claims personalizados del usuario.
     */
    @Override
    public String generarTokenAcceso(UsuarioModel usuario) {
        Map<String, Object> claims = construirClaimsDesdeUsuario(usuario);
        return generarToken(claims, usuario.getUsername(), jwtProperties.getAccessTokenExpiration());
    }

    /**
     * Genera un Refresh Token simple con solo el subject.
     */
    @Override
    public String generarTokenRefresco(UsuarioModel usuario) {
        return generarToken(Collections.emptyMap(), usuario.getUsername(), jwtProperties.getRefreshTokenExpiration());
    }

    /**
     * Extrae el `sub` (sujeto) del token, que en este caso es el username.
     */
    @Override
    public String extraerUsuario(String token) {
        return extraerClaim(token, Claims::getSubject);
    }

    /**
     * Verifica que el token sea válido y esté bien firmado.
     */
    @Override
    public boolean esTokenValido(String token) {
        try {
            Jwts.parser()
                    .verifyWith(obtenerClaveFirma())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public UserDetails crearUserDetailsDesdeToken(String token) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(extraerUsuario(token))
                .password("")
                .authorities(extraerRoles(token))
                .build();
    }

    // --- Métodos privados ---
    private Map<String, Object> construirClaimsDesdeUsuario(UsuarioModel usuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", usuario.getNombre() + " " + usuario.getApellido());
        claims.put("roles", usuario.getRoles()
                .stream()
                .map(RolModel::getNombre)
                .toList());
        return claims;
    }

    private String generarToken(Map<String, Object> claims, String subject, long expiracionMilisegundos) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(now))
                .expiration(new Date(now + expiracionMilisegundos))
                .signWith(obtenerClaveFirma())
                .compact();
    }

    private SecretKey obtenerClaveFirma() {
        byte[] keyBytes = Decoders.BASE64URL.decode(jwtProperties.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extraerTodosLosClaims(String token) {
        return Jwts.parser()
                .verifyWith(obtenerClaveFirma())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extraerClaim(String token, Function<Claims, T> resolver) {
        return resolver.apply(extraerTodosLosClaims(token));
    }

    @SuppressWarnings("unchecked")
    private Collection<GrantedAuthority> extraerRoles(String token) {
        List<String> authorities = extraerClaim(token, claims ->
                (List<String>) claims.get("roles"));

        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
