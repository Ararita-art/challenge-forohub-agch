package foro.hub.api.domain.topico;

public record DatosActualizacionTopico(
        Long id,
        String titulo,
        String mensaje,
        Boolean estatus
) {
}