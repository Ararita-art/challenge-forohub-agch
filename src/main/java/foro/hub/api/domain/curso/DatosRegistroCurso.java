package foro.hub.api.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCurso(
        @NotBlank String nombre,
        @NotBlank String categoria
) {
    public DatosRegistroCurso {
        nombre = nombre != null ? nombre.trim() : null;
        categoria = categoria != null ? categoria.trim() : null;
    }
}