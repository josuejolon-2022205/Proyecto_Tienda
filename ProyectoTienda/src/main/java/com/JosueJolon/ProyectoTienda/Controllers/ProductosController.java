package com.JosueJolon.ProyectoTienda.Controllers;

import com.JosueJolon.ProyectoTienda.Entity.Productos;
import com.JosueJolon.ProyectoTienda.Service.ProductosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductosController {
    private final ProductosService productosService;

    public ProductosController(ProductosService productosService) {
        this.productosService = productosService;
    }

    @GetMapping
    public List<Productos> getAListProductos(){
        return productosService.getAListProductos();
    }

    @PostMapping
    public ResponseEntity<Object> saveProductos(@Valid @RequestBody Productos productos){
        Productos productos1 = productosService.saveProductos(productos);
        return new ResponseEntity<>(productos1, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProductos(@PathVariable Integer id, @Valid @RequestBody Productos productos){
        Productos productos1 = productosService.updateProductos(id, productos);
        return new ResponseEntity<>(productos1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductos(@PathVariable Integer id){
        productosService.deleteProductos(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductosById(@PathVariable Integer id){
        Productos productos = productosService.getProductosById(id);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/producto")
    public String mostrarProducto(){
        return "producto";
    }
}
