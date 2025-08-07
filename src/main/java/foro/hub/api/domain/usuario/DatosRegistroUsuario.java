package foro.hub.api.domain.usuario;

import foro.hub.api.domain.perfil.DatosRegistroPerfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Collections;
import java.util.List;

public record DatosRegistroUsuario(
    @NotBlank String nombre,
    @NotBlank @Email String correoElectronico,
    @NotBlank String contrasena,
    List<DatosRegistroPerfil> perfiles
) {

    public DatosRegistroUsuario {
        perfiles = perfiles != null ? perfiles : Collections.emptyList();
    }
}