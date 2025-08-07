package foro.hub.api.domain.topico;

import java.time.LocalDateTime;

public record DatosListaTopico(

        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        String autor,
        Integer cantidadRespuestas
) {

    public DatosListaTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha(),
                topico.getAutor().getNombre(),
                topico.getRespuestas().size()
        );
    }
}