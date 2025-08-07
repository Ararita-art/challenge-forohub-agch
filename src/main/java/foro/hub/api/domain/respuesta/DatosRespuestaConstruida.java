package foro.hub.api.domain.respuesta;

import java.time.LocalDateTime;

public record DatosRespuestaConstruida(
        Long id,
        Long topicoId,
        String tituloTopico,
        String mensaje,
        String autor,
        LocalDateTime fecha,
        Boolean solucion
) {
    public DatosRespuestaConstruida(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getTopico().getId(),
                respuesta.getTopico().getTitulo(),
                respuesta.getMensaje(),
                respuesta.getAutor().getNombre(),
                respuesta.getFecha(),
                respuesta.getSolucion()
        );
    }
}