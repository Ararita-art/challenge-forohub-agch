package foro.hub.api.domain.topico;

import java.time.LocalDateTime;

public record DatosDescripcionTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        String autor,
        String curso,
        Boolean estatus,
        Integer cantidadRespuestas
) {
    public DatosDescripcionTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre(),
                topico.getEstatus(),
                topico.getRespuestas().size()
        );
    }
}