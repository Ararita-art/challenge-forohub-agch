package foro.hub.api.domain.usuario;

import foro.hub.api.domain.perfil.Perfil;

import java.util.List;
import java.util.stream.Collectors;

public record DatosDescripcionUsuario(
        Long id,
        String nombre,
        String correoElectronico,
        List<String> perfiles
) {
    public DatosDescripcionUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreoElectronico(),
                usuario.getPerfiles().stream()
                        .map(Perfil::getNombre)
                        .sorted()
                        .collect(Collectors.toList())
        );
    }
}