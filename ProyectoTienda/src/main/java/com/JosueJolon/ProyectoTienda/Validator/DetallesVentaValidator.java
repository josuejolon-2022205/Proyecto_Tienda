package com.JosueJolon.ProyectoTienda.Validator;

import com.JosueJolon.ProyectoTienda.Entity.DetallesVenta;
import com.JosueJolon.ProyectoTienda.Entity.Productos;
import com.JosueJolon.ProyectoTienda.Entity.Ventas;
import com.JosueJolon.ProyectoTienda.Exception.Exceptions;
import com.JosueJolon.ProyectoTienda.Repository.ProductosRepository;
import com.JosueJolon.ProyectoTienda.Repository.VentasRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DetallesVentaValidator {

    private final ProductosRepository productosRepository;
    private final VentasRepository ventasRepository;

    public DetallesVentaValidator(ProductosRepository productosRepository, VentasRepository ventasRepository) {
        this.productosRepository = productosRepository;
        this.ventasRepository = ventasRepository;
    }

    public void ValidacionesDetalleVenta(DetallesVenta detallesVenta){

        Productos producto = null;
        Ventas ventas = null;
        double subtotalTotal = detallesVenta.getCantidad() * detallesVenta.getPrecio_unitario();

        List<Productos> productos1 =  productosRepository.findAll();
        for(Productos productos2 : productos1){
            if(productos2.getCodigo_producto().equals(detallesVenta.getProductos_codigo_producto())){
                producto = productos2;
                break;
            }
        }

        List<Ventas> ventas1 = ventasRepository.findAll();
        for(Ventas ventas2 : ventas1){
            if(ventas2.getCodigo_venta().equals(detallesVenta.getVentas_codigo_venta())){
                ventas = ventas2;
                break;
            }
        }

        List<Productos> productosList = productosRepository.findAll();
        for (Productos productos : productosList){
            if(productos.getCodigo_producto().equals(detallesVenta.getProductos_codigo_producto())){
                productos.setStock(productos.getStock() - detallesVenta.getCantidad());
                productosRepository.save(productos);
                break;
            }
        }

        if(producto.getEstado().equals(0)){
            throw new Exceptions("El producto esta inactivo");
        }

        if(producto.getStock() < detallesVenta.getCantidad()){
            throw new Exceptions("no hay esta cantidad del producto en el stock");
        }

        if(Math.abs(detallesVenta.getSubtotal() - subtotalTotal) < 0.01){ // uso el math abs para verificar que aunque sea por unos decimales se va a validar correctamente
            throw new Exceptions("el subtotal no concuerda ");

        }

        if(ventas.getEstado().equals(0)){
            throw new Exceptions("la venta esta inactiva");
        }

        if(!detallesVenta.getPrecio_unitario().equals(producto.getPrecio())){
            throw new Exceptions("el precio unitario no coincide con el precio del producto");
        }

    }
}
