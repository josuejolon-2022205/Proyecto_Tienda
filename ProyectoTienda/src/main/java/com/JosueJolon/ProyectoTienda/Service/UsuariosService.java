package com.JosueJolon.ProyectoTienda.Service;

import com.JosueJolon.ProyectoTienda.Entity.Usuarios;

import java.util.List;

public interface UsuariosService {
    List<Usuarios> getAListUsuarios();
    Usuarios getUsuariosById(Integer id);
    Usuarios saveUsuarios(Usuarios usuarios) throws RuntimeException;
    Usuarios updateUsuarios(Integer id, Usuarios usuarios);
    void deleteUsuarios(Integer id);
}
