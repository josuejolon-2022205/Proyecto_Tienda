package com.JosueJolon.ProyectoTienda.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "Ventas")
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_venta")
    private Integer codigo_venta;

    @Column(name = "fecha_venta")
    @NotNull(message = "la fecha de la venta no puede estar vacia")
    private LocalDate fecha_venta;

    @Column(name = "total")
    @NotNull(message = "el total no puede estar vacio")
    @Positive(message = "el total no puede ser negativo")
    private Double total;

    @Column(name = "estado")
    @NotNull(message = "el estado no puede estar vacio")
    @Min(value = 0)
    @Max(value = 1)
    private Integer estado;

    @Column(name = "clientes_dpi_cliente")
    @NotNull(message = "el dpi del cliente no puede estar vacio")
    @Positive(message = "el dpi del cliente no puede ser negativo")
    private Integer clientes_dpi_cliente;

    @Column(name = "usuarios_codigo_usuario")
    @NotNull(message = "el codigo del usuario no puede estar vacio")
    @Positive(message = "el codigo del usuario no puede ser negativo")
    private Integer usuarios_codigo_usuario;

    public Integer getCodigo_venta() {
        return codigo_venta;
    }

    public void setCodigo_venta(Integer codigo_venta) {
        this.codigo_venta = codigo_venta;
    }

    public LocalDate getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(LocalDate fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getClientes_dpi_cliente() {
        return clientes_dpi_cliente;
    }

    public void setClientes_dpi_cliente(Integer clientes_dpi_cliente) {
        this.clientes_dpi_cliente = clientes_dpi_cliente;
    }

    public Integer getUsuarios_codigo_usuario() {
        return usuarios_codigo_usuario;
    }

    public void setUsuarios_codigo_usuario(Integer usuarios_codigo_usuario) {
        this.usuarios_codigo_usuario = usuarios_codigo_usuario;
    }
}
