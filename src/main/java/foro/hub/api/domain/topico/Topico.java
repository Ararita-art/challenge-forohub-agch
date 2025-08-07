package foro.hub.api.domain.topico;

import foro.hub.api.domain.curso.Curso;
import foro.hub.api.domain.respuesta.Respuesta;
import foro.hub.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha = LocalDateTime.now();
    private Boolean estatus = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas = new ArrayList<>();

    public Topico(DatosRegistroTopico datosRegistroTopico, Usuario autor, Curso curso) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.autor = autor;
        this.curso = curso;
    }

    public void actualizarDatosTopico(@Valid DatosActualizacionTopico datosActualizacionTopico) {
        if (datosActualizacionTopico.titulo() != null && !datosActualizacionTopico.titulo().isBlank()) {
            this.titulo = datosActualizacionTopico.titulo();
        }
        if (datosActualizacionTopico.mensaje() != null && !datosActualizacionTopico.mensaje().isBlank()) {
            this.mensaje = datosActualizacionTopico.mensaje();
        }
        if (datosActualizacionTopico.estatus() != null) {
            this.estatus = datosActualizacionTopico.estatus();
        }
    }

    public void eliminarLogicaTopico() {
        this.estatus = false;
    }
}