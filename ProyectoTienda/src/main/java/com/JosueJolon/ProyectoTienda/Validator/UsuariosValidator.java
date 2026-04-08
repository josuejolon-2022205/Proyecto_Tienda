package com.JosueJolon.ProyectoTienda.Validator;

import com.JosueJolon.ProyectoTienda.Entity.Usuarios;
import com.JosueJolon.ProyectoTienda.Exception.Exceptions;
import com.JosueJolon.ProyectoTienda.Repository.UsuariosRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuariosValidator {
    private final UsuariosRepository usuariosRepository;

    public UsuariosValidator(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public void validarUsuario(Usuarios usuarios){
        List<Usuarios> usuariosList = usuariosRepository.findAll();

        for(Usuarios usuarios1 : usuariosList){
            if(usuarios1.getUsername().equals(usuarios.getUsername())){
                throw new Exceptions("el username ya esta en uso");
            }


            if(usuarios1.getEmail().equals(usuarios.getEmail())){
                throw new Exceptions("el email ya existe");
            }
        }

    }
}
