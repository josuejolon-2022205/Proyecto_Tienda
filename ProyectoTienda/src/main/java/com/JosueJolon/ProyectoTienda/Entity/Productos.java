package com.JosueJolon.ProyectoTienda.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Productos")
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_producto")
    private Integer codigo_producto;

    @Column(name = "nombre_producto")
    @NotBlank(message = "el nombre del producto no puede estar vacio")
    @Size(max = 60, message = "el nombre del producto no puede tener mas de 60 caracteres")
    private String nombre_producto;

    @Column(name = "precio")
    @Positive(message = "el precio no puede ser menor 0")
    @NotNull(message = "el precio no puede estar vacio")
    private Double precio;

    @Column(name = "stock")
    @NotNull(message = "el stock no puede estar vacio")
    @Min(value = 0)
    private Integer stock;

    @Column(name = "estado")
    @NotNull(message = "el estado no puede estar vacio")
    @Min(value = 0)
    @Max(value = 1)
    private Integer estado;

    public Integer getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(Integer codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
