package com.JosueJolon.ProyectoTienda.Service;

import com.JosueJolon.ProyectoTienda.Entity.Clientes;
import com.JosueJolon.ProyectoTienda.Exception.Exceptions;
import com.JosueJolon.ProyectoTienda.Repository.ClientesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesServiceImplements implements ClientesService{
    private final ClientesRepository clientesRepository;

    public ClientesServiceImplements(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @Override
    public List<Clientes> getAListClientes() {
        return clientesRepository.findAll();
    }

    @Override
    public Clientes getClientesById(Integer id) {
        Clientes clientes = clientesRepository.findById(id).orElse(null);
        if(clientes == null){
            throw new IllegalArgumentException();

        }else{
            return clientesRepository.findById(id).orElse(null);
        }
    }

    @Override
    public Clientes saveClientes(Clientes clientes) throws RuntimeException {
        return clientesRepository.save(clientes);
    }

    @Override
    public Clientes updateClientes(Integer id, Clientes clientes) {
        Clientes clientes1 = clientesRepository.findById(id).orElse(null);
        if(clientes1 != null){
            clientes1.setNombre_cliente(clientes.getNombre_cliente());
            clientes1.setApellido_cliente(clientes.getApellido_cliente());
            clientes1.setDireccion(clientes.getDireccion());
            clientes1.setEstado(clientes.getEstado());
        }else{
            throw new Exceptions("el id del cliente no existe");
        }
        return clientesRepository.save(clientes1);
    }

    @Override
    public void deleteClientes(Integer id) {
        Clientes clientes = clientesRepository.findById(id).orElse(null);
        if(clientes == null){
            throw new Exceptions("el id del cliente no existe");
        }
        clientesRepository.deleteById(id);
    }
}
