package foro.hub.api.controller;

import foro.hub.api.domain.usuario.DatosDescripcionUsuario;
import foro.hub.api.domain.usuario.DatosRegistroUsuario;
import foro.hub.api.domain.usuario.Usuario;
import foro.hub.api.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDescripcionUsuario> registrarUsuario(
            @RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        if (usuarioRepository.existsByCorreoElectronico(datosRegistroUsuario.correoElectronico())) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado");
        }

        String contrasenaCodificada = passwordEncoder.encode(datosRegistroUsuario.contrasena());
        Usuario usuario = new Usuario(datosRegistroUsuario, contrasenaCodificada);

        usuario = usuarioRepository.save(usuario);

        URI uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDescripcionUsuario(usuario));
    }
}