package com.JosueJolon.ProyectoTienda.Repository;

import com.JosueJolon.ProyectoTienda.Entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    Usuarios findByUsername(String username);
}
