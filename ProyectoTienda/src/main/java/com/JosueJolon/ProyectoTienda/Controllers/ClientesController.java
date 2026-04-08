package com.JosueJolon.ProyectoTienda.Controllers;

import com.JosueJolon.ProyectoTienda.Entity.Clientes;
import com.JosueJolon.ProyectoTienda.Service.ClientesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {
    private final ClientesService clientesService;

    public ClientesController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping
    public List<Clientes> getAlistClientes(){
        return clientesService.getAListClientes();
    }

    @PostMapping
    public ResponseEntity<Object> saveClientes(@Valid @RequestBody Clientes clientes){
        Clientes clientes1 = clientesService.saveClientes(clientes);
        return new ResponseEntity<>(clientes1, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClientes(@PathVariable Integer id, @Valid @RequestBody Clientes clientes){
        Clientes clientes1 = clientesService.updateClientes(id, clientes);
        return new ResponseEntity<>(clientes1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClientes(@PathVariable Integer id){
        clientesService.deleteClientes(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClientesById(@PathVariable Integer id){
        Clientes clientes = clientesService.getClientesById(id);
        return ResponseEntity.ok(clientes);
    }
}
