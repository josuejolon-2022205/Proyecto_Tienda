package com.JosueJolon.ProyectoTienda.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "detalle_venta")
public class DetallesVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_detalle_venta")
    private Integer codigo_detalle_venta;

    @Column(name = "cantidad")
    @NotNull(message = "la cantidad no puede estar vacia")
    @Positive(message = "la cantidad no puede ser menor a 0")
    private Integer cantidad;

    @Column(name = "precio_unitario")
    @NotNull(message = "el precio unitario no puede estar vacio")
    @Positive(message = "el precio no puede ser menor a 0 ")
    private Double precio_unitario;

    @Column(name = "subtotal")
    @NotNull(message = "el subtotal no puede estar vacio")
    @Positive(message = "el subtotal no puede ser menor a 0")
    private Double subtotal;

    @Column(name = "productos_codigo_producto")
    @NotNull(message = "el codigo del producto no puede estar vacio")
    @Positive(message = "el codigo del producto tiene que ser mayor a 0")
    private Integer productos_codigo_producto;

    @Column(name = "ventas_codigo_venta")
    @NotNull(message = "el codigo de la venta no puede estar vacio")
    @Positive(message = "el codigo del producto tiene que ser mayor a 0")
    private Integer ventas_codigo_venta;

    public Integer getCodigo_detalle_venta() {
        return codigo_detalle_venta;
    }

    public void setCodigo_detalle_venta(Integer codigo_detalle_venta) {
        this.codigo_detalle_venta = codigo_detalle_venta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getProductos_codigo_producto() {
        return productos_codigo_producto;
    }

    public void setProductos_codigo_producto(Integer productos_codigo_producto) {
        this.productos_codigo_producto = productos_codigo_producto;
    }

    public Integer getVentas_codigo_venta() {
        return ventas_codigo_venta;
    }

    public void setVentas_codigo_venta(Integer ventas_codigo_venta) {
        this.ventas_codigo_venta = ventas_codigo_venta;
    }
}
