package com.JosueJolon.ProyectoTienda.Service;

import com.JosueJolon.ProyectoTienda.Entity.Productos;

import java.util.List;

public interface ProductosService {
    List<Productos> getAListProductos();
    Productos getProductosById(Integer id);
    Productos saveProductos(Productos productos) throws RuntimeException;
    Productos updateProductos(Integer id, Productos productos);
    void deleteProductos(Integer id);
}
