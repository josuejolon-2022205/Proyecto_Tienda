package com.JosueJolon.ProyectoTienda.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Clientes")
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dpi_cliente")
    private Integer dpi_cliente;

    @Column(name = "nombre_cliente")
    @NotBlank(message = "el nombre del cliente no puede estar vacio")
    @Size(max = 50, message = "el nombre del cliente no puede tener mas de 50 caracteres")
    private String nombre_cliente;

    @Column(name = "apellido_cliente")
    @NotBlank(message = "el apellido del cliente no puede estar vacio")
    @Size(max = 50, message = "el apellido del cliente no puede tener mas de 50 caracteres")
    private String apellido_cliente;

    @Column(name = "direccion")
    @NotBlank(message = "la direcion no puede estar vacia")
    @Size(max = 100, message = "la direccion no puede tener mas de 100 caracteres")
    private String direccion;

    @Column(name = "estado")
    @NotNull(message = "el estado no puede estar vacio")
    @Min(value = 0)
    @Max(value = 1)
    private Integer estado;

    public Integer getDpi_cliente() {
        return dpi_cliente;
    }

    public void setDpi_cliente(Integer dpi_cliente) {
        this.dpi_cliente = dpi_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getApellido_cliente() {
        return apellido_cliente;
    }

    public void setApellido_cliente(String apellido_cliente) {
        this.apellido_cliente = apellido_cliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
