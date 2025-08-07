package foro.hub.api.domain.topico;

import foro.hub.api.domain.curso.Curso;
import foro.hub.api.domain.curso.CursoRepository;
import foro.hub.api.domain.usuario.Usuario;
import foro.hub.api.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    @Transactional
    public Topico crearTopico(DatosRegistroTopico datos) {
        Usuario autor = usuarioRepository.findById(datos.autorId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        Curso curso = cursoRepository.findById(datos.cursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));

        Topico topico = new Topico(datos, autor, curso);
        return topicoRepository.save(topico);
    }

    public Page<DatosListaTopico> enlistarTopico(Pageable paginacion) {
        return topicoRepository.findByEstatusTrue(paginacion)
                .map(DatosListaTopico::new);
    }

    public DatosDescripcionTopico detallarTopico(Long id) {
        return topicoRepository.findByIdAndEstatusTrue(id)
                .map(DatosDescripcionTopico::new)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));
    }

    @Transactional
    public DatosDescripcionTopico renovarTopico(DatosActualizacionTopico datosActualizacionTopico) {
        Topico topico = topicoRepository.findById(datosActualizacionTopico.id())
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        topico.actualizarDatosTopico(datosActualizacionTopico);
        return new DatosDescripcionTopico(topico);
    }

    @Transactional
    public void suprimirTopico(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        topico.eliminarLogicaTopico();
    }
}