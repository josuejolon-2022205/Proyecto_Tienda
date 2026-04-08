package com.JosueJolon.ProyectoTienda.Repository;

import com.JosueJolon.ProyectoTienda.Entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Clientes, Integer> {
}
