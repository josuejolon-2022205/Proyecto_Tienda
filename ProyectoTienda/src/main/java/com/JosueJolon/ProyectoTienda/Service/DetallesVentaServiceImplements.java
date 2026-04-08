package com.JosueJolon.ProyectoTienda.Service;

import com.JosueJolon.ProyectoTienda.Entity.DetallesVenta;
import com.JosueJolon.ProyectoTienda.Exception.Exceptions;
import com.JosueJolon.ProyectoTienda.Repository.DetallesVentasRepository;
import com.JosueJolon.ProyectoTienda.Validator.DetallesVentaValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallesVentaServiceImplements implements DetallesVentaService{
    private final DetallesVentasRepository detallesVentasRepository;
    private final DetallesVentaValidator detallesVentaValidator;

    public DetallesVentaServiceImplements(DetallesVentasRepository detallesVentasRepository, DetallesVentaValidator detallesVentaValidator) {
        this.detallesVentasRepository = detallesVentasRepository;
        this.detallesVentaValidator = detallesVentaValidator;
    }

    @Override
    public List<DetallesVenta> getAListDetallesVenta() {
        return detallesVentasRepository.findAll();
    }

    @Override
    public DetallesVenta getDetallesVentaById(Integer id) {
        DetallesVenta detallesVenta = detallesVentasRepository.findById(id).orElse(null);
        if(detallesVenta == null){
            throw new IllegalArgumentException();

        }else {
            return detallesVentasRepository.findById(id).orElse(null);
        }
    }

    @Override
    public DetallesVenta saveDetallesVenta(DetallesVenta detallesVenta) throws RuntimeException {
        detallesVentaValidator.ValidacionesDetalleVenta(detallesVenta);
        return detallesVentasRepository.save(detallesVenta);
    }

    @Override
    public DetallesVenta updateDetallesVenta(Integer id, DetallesVenta detallesVenta) {
        DetallesVenta detallesVenta1 = detallesVentasRepository.findById(id).orElse(null);
        if(detallesVenta1 != null){

            detallesVenta1.setCantidad(detallesVenta.getCantidad());
            detallesVenta1.setPrecio_unitario(detallesVenta.getPrecio_unitario());
            detallesVenta1.setSubtotal(detallesVenta.getSubtotal());
            detallesVenta1.setProductos_codigo_producto(detallesVenta.getProductos_codigo_producto());
            detallesVenta1.setVentas_codigo_venta(detallesVenta.getVentas_codigo_venta());
        }else{
            throw new Exceptions("el id del detalle de la venta no existe");
        }
        return detallesVentasRepository.save(detallesVenta1);
    }

    @Override
    public void deleteDetallesVenta(Integer id) {
        DetallesVenta detallesVenta = detallesVentasRepository.findById(id).orElse(null);
        if(detallesVenta == null){
            throw new Exceptions("el id del detalle de la venta no existe");
        }
        detallesVentasRepository.deleteById(id);
    }
}
