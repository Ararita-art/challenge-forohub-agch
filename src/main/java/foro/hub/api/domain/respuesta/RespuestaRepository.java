package foro.hub.api.domain.respuesta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

    @EntityGraph(attributePaths = {"autor", "topico"})
    Page<Respuesta> findByTopicoIdAndSolucionTrue(Long topicoId, Pageable paginacion);

    @EntityGraph(attributePaths = {"autor", "topico"})
    Page<Respuesta> findByTopicoId(Long topicoId, Pageable paginacion);

    @EntityGraph(attributePaths = {"autor", "topico"})
    Optional<Respuesta> findByIdAndTopicoId(Long id, Long topicoId);

    boolean existsByTopicoIdAndSolucionTrue(Long topicoId);

    @Modifying
    @Query("UPDATE Respuesta r SET r.solucion = false WHERE r.topico.id = :topicoId AND r.solucion = true")
    void desmarcarOtrasSoluciones(@Param("topicoId") Long topicoId);
}