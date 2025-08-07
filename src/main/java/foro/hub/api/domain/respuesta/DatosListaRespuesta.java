package foro.hub.api.domain.respuesta;

import java.time.LocalDateTime;

public record DatosListaRespuesta(
        Long id,
        String mensaje,
        String autor,
        LocalDateTime fecha,
        Boolean solucion
) {
    public DatosListaRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getAutor().getNombre(),
                respuesta.getFecha(),
                respuesta.getSolucion()
        );
    }
}