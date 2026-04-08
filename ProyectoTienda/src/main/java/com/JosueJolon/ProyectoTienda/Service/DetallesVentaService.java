package com.JosueJolon.ProyectoTienda.Service;

import com.JosueJolon.ProyectoTienda.Entity.DetallesVenta;

import java.util.List;

public interface DetallesVentaService {
    List<DetallesVenta> getAListDetallesVenta();
    DetallesVenta getDetallesVentaById(Integer id);
    DetallesVenta saveDetallesVenta(DetallesVenta detallesVenta) throws RuntimeException;
    DetallesVenta updateDetallesVenta(Integer id, DetallesVenta detallesVenta);
    void deleteDetallesVenta(Integer id);
}
