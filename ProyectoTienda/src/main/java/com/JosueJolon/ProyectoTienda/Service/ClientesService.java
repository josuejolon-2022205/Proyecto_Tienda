package com.JosueJolon.ProyectoTienda.Service;


import com.JosueJolon.ProyectoTienda.Entity.Clientes;

import java.util.List;

public interface ClientesService {
    List<Clientes> getAListClientes();
    Clientes getClientesById(Integer id);
    Clientes saveClientes(Clientes clientes) throws RuntimeException;
    Clientes updateClientes(Integer id, Clientes clientes);
    void deleteClientes(Integer id);
}
