package foro.hub.api.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(
        @NotBlank String mensaje,
        @NotNull Long topicoId,
        @NotNull Long autorId,
        Boolean solucion
) {
    public DatosRegistroRespuesta {
        solucion = solucion != null ? solucion : false;
    }
}