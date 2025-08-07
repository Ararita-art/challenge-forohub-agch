package foro.hub.api.domain.usuario;

import foro.hub.api.domain.perfil.DatosRegistroPerfil;
import foro.hub.api.domain.perfil.Perfil;
import foro.hub.api.domain.perfil.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario crearUsuarioConPerfilBasico(DatosRegistroUsuario datosRegistroUsuario) {
        if (usuarioRepository.existsByCorreoElectronico(datosRegistroUsuario.correoElectronico())) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado");
        }

        String contrasenaCodificada = passwordEncoder.encode(datosRegistroUsuario.contrasena());
        Usuario usuario = new Usuario(datosRegistroUsuario, contrasenaCodificada);

        // Asignar perfil básico cuando no se especifican perfiles
        if (usuario.getPerfiles().isEmpty()) {
            Perfil perfilUsuario = new Perfil(new DatosRegistroPerfil("ROLE_USUARIO"));
            perfilUsuario.setUsuario(usuario);
            usuario.getPerfiles().add(perfilUsuario);
        }

        return usuarioRepository.save(usuario);
    }
}