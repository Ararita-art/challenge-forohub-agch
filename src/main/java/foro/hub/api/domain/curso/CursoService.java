package foro.hub.api.domain.curso;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CursoService {

        private final CursoRepository cursoRepository;

        @Transactional
        public Curso registrarCurso(DatosRegistroCurso datos) {
        if (cursoRepository.existsByNombre(datos.nombre())) {
            throw new IllegalArgumentException("Ya existe un curso con este nombre");
        }

        Curso curso = new Curso(datos);
        return cursoRepository.save(curso);
    }

        public Page<DatosListaCurso> listarCurso(Pageable paginacion) {
        return cursoRepository.findAll(paginacion)
                .map(DatosListaCurso::new);
    }

        public DatosRespuestaCurso obtenerCurso(Long id) {
        return cursoRepository.findById(id)
                .map(DatosRespuestaCurso::new)
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));
    }

        public Page<DatosListaCurso> listarCursoCategoria(String categoria, Pageable paginacion) {
        return cursoRepository.findByCategoria(categoria.toUpperCase(), paginacion)
                .map(DatosListaCurso::new);
    }
}
