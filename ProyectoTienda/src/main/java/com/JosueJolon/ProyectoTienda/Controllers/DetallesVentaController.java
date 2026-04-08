package com.JosueJolon.ProyectoTienda.Controllers;

import com.JosueJolon.ProyectoTienda.Entity.DetallesVenta;
import com.JosueJolon.ProyectoTienda.Service.DetallesVentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detallesventa")
public class DetallesVentaController {
    private final DetallesVentaService detallesVentaService;

    public DetallesVentaController(DetallesVentaService detallesVentaService) {
        this.detallesVentaService = detallesVentaService;
    }

    @GetMapping
    public List<DetallesVenta> getAListDetallesVenta(){
        return detallesVentaService.getAListDetallesVenta();
    }

    @PostMapping
    public ResponseEntity<Object> saveDetallesVenta(@Valid @RequestBody DetallesVenta detallesVenta ){
        DetallesVenta detallesVenta1 = detallesVentaService.saveDetallesVenta(detallesVenta);
        return new ResponseEntity<>(detallesVenta1, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDetallesVenta(@PathVariable Integer id, @Valid @RequestBody DetallesVenta detallesVenta){
        DetallesVenta detallesVenta1 = detallesVentaService.updateDetallesVenta(id, detallesVenta);
        return new ResponseEntity<>(detallesVenta1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDetallesVenta(@PathVariable Integer id){
        detallesVentaService.deleteDetallesVenta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDetallesVentaById(@PathVariable Integer id){
        DetallesVenta detallesVenta = detallesVentaService.getDetallesVentaById(id);
        return ResponseEntity.ok(detallesVenta);
    }
}
