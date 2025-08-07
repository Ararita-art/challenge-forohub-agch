package foro.hub.api.infrastructure.security;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public record DatosErrorAuthentication(
        String mensaje,
        Instant timestamp,
        HttpStatus status
) {
    public DatosErrorAuthentication(String mensaje, HttpStatus status) {
        this(mensaje, Instant.now(), status);
    }
}