package com.JosueJolon.ProyectoTienda.Controllers;

import com.JosueJolon.ProyectoTienda.Entity.DetallesVenta;
import com.JosueJolon.ProyectoTienda.Service.DetallesVentaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/detalleVenta")
public class DetalleVentaViewController {
    private final DetallesVentaService detallesVentaService;

    public DetalleVentaViewController(DetallesVentaService detallesVentaService) {
        this.detallesVentaService = detallesVentaService;
    }

    @GetMapping
    public String listarDetalle(Model model){
        List<DetallesVenta> listar = detallesVentaService.getAListDetallesVenta();
        model.addAttribute("detallesVenta", listar);
        return "detalleVenta";
    }

    @PostMapping("/guardarDetalleVenta")
    public String guardarDetalle(@Valid @ModelAttribute ("detalleVentaFormu")DetallesVenta detallesVenta, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            model.addAttribute("detallesVenta ", detallesVentaService.getAListDetallesVenta());
            return "detalleVenta";
        }
        try {
            detallesVentaService.saveDetallesVenta(detallesVenta);
            redirectAttributes.addFlashAttribute("exito", "el detalle de la venta fue creado");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "no se creo el detalle de la venta");
        }
        return "redirect:/detalleVenta";
    }

    @GetMapping("/editarDetalleVenta/{id}")
    public String seleccionEdit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("detallesVenta", detallesVentaService.getAListDetallesVenta());
            model.addAttribute("detallesVentaFormu", detallesVentaService.getDetallesVentaById(id));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("advertencia", "el id del detalle de la venta no fue encontrado");
        }
        return "detalleVenta";
    }

    @PostMapping("/eliminarDetalleVenta/{id}")
    public String guardarDetalle(@PathVariable Integer id, RedirectAttributes redirectAttributes ){
        try {
            detallesVentaService.deleteDetallesVenta(id);
            redirectAttributes.addFlashAttribute("info", "el detalle de la venta fue eliminado");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("advertencia", "el id del detalle de la venta no existe");

        }
        return "redirect:/detalleVenta";
    }

    @GetMapping("/buscarDetalleVenta")
    public String buscarDetalle(@RequestParam Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            DetallesVenta detallesVenta = detallesVentaService.getDetallesVentaById(id);
            model.addAttribute("detallesVenta", detallesVentaService.getAListDetallesVenta());
            model.addAttribute("detallesVentaFormu", detallesVenta);
            return "detalleVenta";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("advertencia", "el id del detalle de la venta no existe");
            return "redirect:/detalleVenta";
        }
    }

    @PostMapping("/actualizarDetalleVenta/{id}")
    public String actualizarDetalle(@PathVariable Integer id, @Valid @ModelAttribute ("detallesVentaFormu") DetallesVenta detallesVenta, Model model, RedirectAttributes redirectAttributes, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("detallesVenta", detallesVentaService.getAListDetallesVenta());
            model.addAttribute("detalleVenta", detallesVenta);
            return "detalleVenta";
        }
        try {
            detallesVentaService.updateDetallesVenta(id, detallesVenta);
            redirectAttributes.addFlashAttribute("exito", "el detalle de la venta fue actualizado");
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "no se pudo actualizar el detalle de la venta");
        }
        return "redirect:/detalleVenta";

    }


}
