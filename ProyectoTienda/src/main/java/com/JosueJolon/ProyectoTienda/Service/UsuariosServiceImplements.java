package com.JosueJolon.ProyectoTienda.Service;

import com.JosueJolon.ProyectoTienda.Entity.Usuarios;
import com.JosueJolon.ProyectoTienda.Exception.Exceptions;
import com.JosueJolon.ProyectoTienda.Repository.UsuariosRepository;
import com.JosueJolon.ProyectoTienda.Validator.UsuariosValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosServiceImplements implements  UsuariosService{
    private final UsuariosRepository usuariosRepository;
    private final UsuariosValidator usuariosValidator;

    public UsuariosServiceImplements(UsuariosRepository usuariosRepository, UsuariosValidator usuariosValidator) {
        this.usuariosRepository = usuariosRepository;
        this.usuariosValidator = usuariosValidator;
    }

    @Override
    public List<Usuarios> getAListUsuarios() {
        return usuariosRepository.findAll();
    }

    @Override
    public Usuarios getUsuariosById(Integer id) {
        Usuarios usuarios = usuariosRepository.findById(id).orElse(null);
        if( usuarios == null){
            throw new IllegalArgumentException();
        }else {
            return usuariosRepository.findById(id).orElse(null);
        }
    }

    @Override
    public Usuarios login(String usuario, String password) {
        Usuarios u = usuariosRepository.findByUsername(usuario);

        if(u != null && u.getPassword().equals(password)){
            return u;
        }
        return null;
    }

    @Override
    public Usuarios registrar(String usuario, String password) {
        return null;
    }

    @Override
    public Usuarios saveUsuarios(Usuarios usuarios) throws RuntimeException {
        usuariosValidator.validarUsuario(usuarios);
        return usuariosRepository.save(usuarios);
    }

    @Override
    public Usuarios updateUsuarios(Integer id, Usuarios usuarios) {
        Usuarios usuarios1 = usuariosRepository.findById(id).orElse(null);
        if(usuarios1 != null){
            usuariosValidator.validarUsuario(usuarios);
            usuarios1.setUsername(usuarios.getUsername());
            usuarios1.setPassword(usuarios.getPassword());
            usuarios1.setEmail(usuarios.getEmail());
            usuarios1.setRol(usuarios.getRol());
            usuarios1.setEstado(usuarios.getEstado());
        }else{
            throw new Exceptions("el id del usuario no existe");
        }
        return usuariosRepository.save(usuarios1);
    }

    @Override
    public void deleteUsuarios(Integer id) {
        Usuarios usuarios = usuariosRepository.findById(id).orElse(null);
        if(usuarios == null){
            throw new Exceptions("el id del usuario no existe");
        }
        usuariosRepository.deleteById(id);
    }
}
