package com.JosueJolon.ProyectoTienda.Validator;

import com.JosueJolon.ProyectoTienda.Entity.Clientes;
import com.JosueJolon.ProyectoTienda.Entity.Usuarios;
import com.JosueJolon.ProyectoTienda.Entity.Ventas;
import com.JosueJolon.ProyectoTienda.Exception.Exceptions;
import com.JosueJolon.ProyectoTienda.Repository.ClientesRepository;
import com.JosueJolon.ProyectoTienda.Repository.UsuariosRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VentasValidator {
    private final ClientesRepository clientesRepository;
    private final UsuariosRepository usuariosRepository;

    public VentasValidator(ClientesRepository clientesRepository, UsuariosRepository usuariosRepository) {
        this.clientesRepository = clientesRepository;
        this.usuariosRepository = usuariosRepository;
    }

    public void ValidarVentas(Ventas ventas){
        Clientes clientes = null;
        List<Clientes> clientes1 =  clientesRepository.findAll();
        for(Clientes clientesList : clientes1){
            if (clientesList.getDpi_cliente().equals(ventas.getClientes_dpi_cliente())){
                clientes = clientesList;
                break;
            }
        }

        if(clientes == null){
            throw new Exceptions("el dpi del cliente no existe");
        }

        Usuarios usuarios = null;
        List<Usuarios> usuariosList = usuariosRepository.findAll();
        for (Usuarios usuarios1 : usuariosList){
            if(usuarios1.getCodigo_usuario().equals(ventas.getUsuarios_codigo_usuario())){
                usuarios = usuarios1;
                break;
            }
        }

        if (clientes.getEstado().equals(0)){
            throw new Exceptions("el cliente esta inactivo");
        }

        if(usuarios.getEstado().equals(0)){
            throw new Exceptions("el usuario esta inactivo");
        }
    }

}
