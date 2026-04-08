package com.JosueJolon.ProyectoTienda.Controllers;

import com.JosueJolon.ProyectoTienda.Entity.Ventas;
import com.JosueJolon.ProyectoTienda.Service.VentasService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentasController {
    private final VentasService ventasService;

    public VentasController(VentasService ventasService) {
        this.ventasService = ventasService;
    }

    @GetMapping
    public List<Ventas> getAListVentas(){
        return ventasService.getAListVentas();
    }

    @PostMapping
    public ResponseEntity<Object> saveVentas(@Valid @RequestBody Ventas ventas){
        Ventas ventas1 = ventasService.saveVentas(ventas);
        return new ResponseEntity<>(ventas1, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVentas(@PathVariable Integer id, @Valid @RequestBody Ventas ventas){
        Ventas ventas1 = ventasService.updateVentas(id, ventas);
        return new ResponseEntity<>(ventas1, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVentas(@PathVariable Integer id){
        ventasService.deleteVentas(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getVentasById(@PathVariable Integer id){
        Ventas ventas = ventasService.getVentasById(id);
        return ResponseEntity.ok(ventas);
    }
}
