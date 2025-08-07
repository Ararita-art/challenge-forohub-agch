package foro.hub.api.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @EntityGraph(attributePaths = {"autor", "curso"})
    Page<Topico> findByEstatusTrue(Pageable paginacion);

    @EntityGraph(attributePaths = {"autor", "curso", "respuestas"})
    Optional<Topico> findByIdAndEstatusTrue(Long id);

    boolean existsByIdAndAutorId(Long topicoId, Long usuarioId);
}