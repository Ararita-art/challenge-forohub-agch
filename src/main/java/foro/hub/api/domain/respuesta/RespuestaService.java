package foro.hub.api.domain.respuesta;

import foro.hub.api.domain.topico.Topico;
import foro.hub.api.domain.topico.TopicoRepository;
import foro.hub.api.domain.usuario.Usuario;
import foro.hub.api.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RespuestaService {

    private final RespuestaRepository respuestaRepository;
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public DatosRespuestaConstruida crearRespuesta(DatosRegistroRespuesta datosRegistroRespuesta) {
        Topico topico = topicoRepository.findById(datosRegistroRespuesta.topicoId())
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        Usuario autor = usuarioRepository.findById(datosRegistroRespuesta.autorId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        if (Boolean.TRUE.equals(datosRegistroRespuesta.solucion()) && respuestaRepository.existsByTopicoIdAndSolucionTrue(datosRegistroRespuesta.topicoId())) {
            respuestaRepository.desmarcarOtrasSoluciones(datosRegistroRespuesta.topicoId());
        }

        Respuesta respuesta = new Respuesta(datosRegistroRespuesta, topico, autor);
        respuesta = respuestaRepository.save(respuesta);

        return new DatosRespuestaConstruida(respuesta);
    }

    @Transactional
    public DatosRespuestaConstruida marcarSolucionado(Long respuestaId, Long topicoId) {
        Respuesta respuesta = respuestaRepository.findByIdAndTopicoId(respuestaId, topicoId)
                .orElseThrow(() -> new EntityNotFoundException("Respuesta no encontrada"));

        respuestaRepository.desmarcarOtrasSoluciones(topicoId);
        respuesta.marcarSolucionado();

        return new DatosRespuestaConstruida(respuesta);
    }

    public Page<DatosListaRespuesta> listarPorTopico(Long topicoId, Pageable paginacion) {
        return respuestaRepository.findByTopicoId(topicoId, paginacion)
                .map(DatosListaRespuesta::new);
    }
}