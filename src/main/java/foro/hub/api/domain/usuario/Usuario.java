package foro.hub.api.domain.usuario;

import foro.hub.api.domain.perfil.Perfil;
import foro.hub.api.domain.respuesta.Respuesta;
import foro.hub.api.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    @Column(name = "correo_electronico")
    private String correoElectronico;
    private String contrasena;

    //Usuario tiene muchos perfiles
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Perfil> perfiles = new ArrayList<>();

    //Usuario crea muchos tópicos
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Topico> topicos = new ArrayList<>();

    //Usuario escribe muchas respuestas
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Respuesta> respuestas = new ArrayList<>();

    public Usuario(DatosRegistroUsuario datosRegistroUsuario, String contrasenaCodificada) {
        this.nombre = datosRegistroUsuario.nombre();
        this.correoElectronico = datosRegistroUsuario.correoElectronico();
        this.contrasena = contrasenaCodificada;

        if (datosRegistroUsuario.perfiles() != null && !datosRegistroUsuario.perfiles().isEmpty()) {
            datosRegistroUsuario.perfiles()
                    .stream()
                    .map(Perfil::new)
                    .forEach(perfil -> {
                        perfil.setUsuario(this);
                        this.perfiles.add(perfil);
                    });
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfiles.stream()
                .map(perfil -> new SimpleGrantedAuthority(perfil.getNombre()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correoElectronico;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}