package foro.hub.api.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import foro.hub.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.tokenJwt.secret}")
    private String secret;

    public String generarTokenJwt(Usuario usuario){

        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API foro.hub")
                    .withSubject(usuario.getUsername())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error al generar token JWT.", exception);
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-06:00"));
    }

    public String getSubject (String tokenJwtCliente){

        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API foro.hub")
                    .build()
                    .verify(tokenJwtCliente)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("El token JWT es inválido o está expirado.");
        }
    }
}