package foro.hub.api.domain.usuario;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByCorreoElectronico(String correoElectronico);

    @EntityGraph(attributePaths = "perfiles")
    Optional<Usuario> findByCorreoElectronico(String correoElectronico);
}