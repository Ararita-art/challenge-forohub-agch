package foro.hub.api.domain.curso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    @EntityGraph(attributePaths = "topicos")
    Optional<Curso> findByNombre(String nombre);

    @EntityGraph(attributePaths = "topicos")
    Optional<Curso> findByNombreAndCategoria(String nombre, String categoria);

    boolean existsByNombre(String nombre);

    @Query("SELECT c FROM Curso c WHERE c.categoria = :categoria ORDER BY c.nombre")
    Page<Curso> findByCategoria(@Param("categoria") String categoria, Pageable pageable);
}