package foro.hub.api.domain.perfil;

import foro.hub.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Perfil")
@Table(name = "perfiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Perfil(DatosRegistroPerfil datosRegistroPerfil) {
        this.nombre = datosRegistroPerfil.nombre().toUpperCase();
    }

    // Metodo para establecer relación entre usuario y perfil
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        if (usuario != null && !usuario.getPerfiles().contains(this)) {
            usuario.getPerfiles().add(this);
        }
    }
}