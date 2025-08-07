package foro.hub.api.controller;

import foro.hub.api.domain.usuario.DatosAutenticacionUsuario;
import foro.hub.api.domain.usuario.Usuario;
import foro.hub.api.infrastructure.security.DatosTokenJWT;
import foro.hub.api.infrastructure.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticacionController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<DatosTokenJWT> iniciarSesion(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {

        var tokenAuthentication = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.correoElectronico(), datosAutenticacionUsuario.contrasena());
        var authentication = authenticationManager.authenticate(tokenAuthentication);
        var usuario = (Usuario) authentication.getPrincipal();

        var tokenJWT = tokenService.generarTokenJwt(usuario);

        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }
}