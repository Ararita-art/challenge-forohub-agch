package foro.hub.api.domain.perfil;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroPerfil(
        @NotBlank String nombre
) {
    public DatosRegistroPerfil {
        nombre = nombre != null ? nombre.trim() : null;
    }
}