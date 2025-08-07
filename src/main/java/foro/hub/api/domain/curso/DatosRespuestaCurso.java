package foro.hub.api.domain.curso;

public record DatosRespuestaCurso(
        Long id,
        String nombre,
        String categoria,
        Integer cantidadTopicos
) {
    public DatosRespuestaCurso(Curso curso) {
        this(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria(),
                curso.getTopicos().size()
        );
    }
}