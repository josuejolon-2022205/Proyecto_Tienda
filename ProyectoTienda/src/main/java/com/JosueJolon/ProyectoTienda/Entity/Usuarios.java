package com.JosueJolon.ProyectoTienda.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_usuario")
    private Integer codigo_usuario;

    @Column(name = "username")
    @Size(max = 45, message = "el nombre del usuario no puede tener mas de 45 caracteres")
    @NotBlank(message = "el nombre del usuario no puede estar vacio")
    private String username;

    @Column(name = "password")
    @NotBlank( message = "la contraseña no puede estar vacia")
    @Size(max = 45, message = "la contraseña no puede tener mas de 45 caracteres")
    private String password;

    @Column(name = "email")
    @NotBlank(message = "el email no puede estar vacio")
    @Size(max = 60, message = "el email no puede tener mas de 60 caracteres")
    private String email;

    @Column(name = "rol")
    @NotBlank(message = "el rol no puede estar vacio")
    @Size(max = 45, message = "el rol no puede tener mas de 45 caracteres")
    private String rol;

    @Column(name = "estado")
    @NotNull(message = "el estado no puede estar vacio")
    @Min(value = 0)
    @Max(value = 1)
    private Integer estado;

    public Integer getCodigo_usuario() {
        return codigo_usuario;
    }

    public void setCodigo_usuario(Integer codigo_usuario) {
        this.codigo_usuario = codigo_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
