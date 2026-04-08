package com.JosueJolon.ProyectoTienda.Service;

import com.JosueJolon.ProyectoTienda.Entity.Ventas;
import com.JosueJolon.ProyectoTienda.Exception.Exceptions;
import com.JosueJolon.ProyectoTienda.Repository.VentasRepository;
import com.JosueJolon.ProyectoTienda.Validator.VentasValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentasServiceImplements implements VentasService {
    private final VentasRepository ventasRepository;
    private final VentasValidator ventasValidator;

    public VentasServiceImplements(VentasRepository ventasRepository, VentasValidator ventasValidator) {
        this.ventasRepository = ventasRepository;
        this.ventasValidator = ventasValidator;
    }

    @Override
    public List<Ventas> getAListVentas() {
        return ventasRepository.findAll();
    }

    @Override
    public Ventas getVentasById(Integer id) {
        Ventas ventas = ventasRepository.findById(id).orElse(null);
        if(ventas == null){
            throw new IllegalArgumentException();
        }else{
            return ventasRepository.findById(id).orElse(null);
        }
    }

    @Override
    public Ventas saveVentas(Ventas ventas) throws RuntimeException {
        ventasValidator.ValidarVentas(ventas);
        return ventasRepository.save(ventas);
    }

    @Override
    public Ventas updateVentas(Integer id, Ventas ventas) {
        Ventas ventas1 = ventasRepository.findById(id).orElse(null);
        if(ventas1 != null){
            ventasValidator.ValidarVentas(ventas);
            ventas1.setFecha_venta(ventas.getFecha_venta());
            ventas1.setTotal(ventas.getTotal());
            ventas1.setEstado(ventas.getEstado());
            ventas1.setClientes_dpi_cliente(ventas.getClientes_dpi_cliente());
            ventas1.setUsuarios_codigo_usuario(ventas.getUsuarios_codigo_usuario());
        }else{
            throw new Exceptions("el id de la venta no existe");
        }
        return ventasRepository.save(ventas1);
    }

    @Override
    public void deleteVentas(Integer id) {
        Ventas ventas = ventasRepository.findById(id).orElse(null);
        if(ventas == null){
            throw new Exceptions("el id de la venta no existe");
        }
        ventasRepository.deleteById(id);
    }
}
