package foro.hub.api.domain.perfil;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    @EntityGraph(attributePaths = "usuario")
    Optional<Perfil> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    @Query("SELECT p FROM Perfil p WHERE p.usuario.id = :usuarioId")
    List<Perfil> findAllByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Modifying
    @Query("DELETE FROM Perfil p WHERE p.usuario.id = :usuarioId")
    void deleteAllByUsuarioId(@Param("usuarioId") Long usuarioId);
}