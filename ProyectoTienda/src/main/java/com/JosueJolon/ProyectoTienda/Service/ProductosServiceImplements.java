package com.JosueJolon.ProyectoTienda.Service;

import com.JosueJolon.ProyectoTienda.Entity.Productos;
import com.JosueJolon.ProyectoTienda.Exception.Exceptions;
import com.JosueJolon.ProyectoTienda.Repository.ProductosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosServiceImplements implements ProductosService {
    private final ProductosRepository productosRepository;

    public ProductosServiceImplements(ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }

    @Override
    public List<Productos> getAListProductos() {
        return productosRepository.findAll();
    }

    @Override
    public Productos getProductosById(Integer id) {
        Productos productos = productosRepository.findById(id).orElse(null);
        if(productos == null){
            throw new IllegalArgumentException();
        }else{
            return productosRepository.findById(id).orElse(null);
        }
    }

    @Override
    public Productos saveProductos(Productos productos) throws RuntimeException {
        return productosRepository.save(productos);
    }

    @Override
    public Productos updateProductos(Integer id, Productos productos) {
        Productos productos1 = productosRepository.findById(id).orElse(null);
        if (productos1 != null) {
            productos1.setNombre_producto(productos.getNombre_producto());
            productos1.setPrecio(productos.getPrecio());
            productos1.setStock(productos.getStock());
            productos1.setEstado(productos.getEstado());
        } else {
            throw new Exceptions("el id del producto no existe");
        }
        return productosRepository.save(productos1);
    }

    @Override
    public void deleteProductos(Integer id) {
        Productos productos = productosRepository.findById(id).orElse(null);
        if(productos == null){
            throw new Exceptions("el id del producto no existe");
        }
        productosRepository.deleteById(id);
    }
}
