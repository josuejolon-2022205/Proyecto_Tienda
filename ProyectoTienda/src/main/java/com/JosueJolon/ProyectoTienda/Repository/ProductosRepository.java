package com.JosueJolon.ProyectoTienda.Repository;

import com.JosueJolon.ProyectoTienda.Entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosRepository extends JpaRepository<Productos, Integer> {
}
