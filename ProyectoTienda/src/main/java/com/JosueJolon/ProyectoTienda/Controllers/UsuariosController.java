package com.JosueJolon.ProyectoTienda.Controllers;

import com.JosueJolon.ProyectoTienda.Entity.Usuarios;
import com.JosueJolon.ProyectoTienda.Service.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {
    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping
    public List<Usuarios> getAListUsuarios(){
        return usuariosService.getAListUsuarios();
    }

    @PostMapping
    public ResponseEntity<Object> saveUsuarios(@Valid @RequestBody Usuarios usuarios){
        Usuarios usuarios1 = usuariosService.saveUsuarios(usuarios);
        return new ResponseEntity<>(usuarios1, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsuarios(@PathVariable Integer id, @Valid @RequestBody Usuarios usuarios){
        Usuarios usuarios1 = usuariosService.updateUsuarios(id, usuarios);
        return new ResponseEntity<>(usuarios1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuarios(@PathVariable Integer id){
        usuariosService.deleteUsuarios(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsuariosById(@PathVariable Integer id){
        Usuarios usuarios = usuariosService.getUsuariosById(id);
        return ResponseEntity.ok(usuarios);
    }

}
