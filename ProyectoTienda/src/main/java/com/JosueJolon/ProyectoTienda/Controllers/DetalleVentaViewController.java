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
        model.addAttribute("detallesVenta", detallesVentaService.getAListDetallesVenta());
        model.addAttribute("detallesVentaFormu", new DetallesVenta());
        return "detalleVenta";
    }

    @PostMapping("/guardarDetalleVenta")
    public String guardarDetalle(@Valid @ModelAttribute ("detallesVentaFormu")DetallesVenta detallesVenta, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            model.addAttribute("detallesVenta", detallesVentaService.getAListDetallesVenta());
            return "detalleVenta";
        }
        detallesVentaService.saveDetallesVenta(detallesVenta);
        redirectAttributes.addFlashAttribute("exito", "el detalle de la venta fue creado");
        return "redirect:/detalleVenta";
    }

    @GetMapping("/editarDetalleVenta/{id}")
    public String seleccionEdit(@PathVariable Integer id, Model model) {
        model.addAttribute("detallesVenta", detallesVentaService.getAListDetallesVenta());
        model.addAttribute("detallesVentaFormu", detallesVentaService.getDetallesVentaById(id));
        return "detalleVenta";
    }

    @PostMapping("/eliminarDetalleVenta/{id}")
    public String guardarDetalle(@PathVariable Integer id, RedirectAttributes redirectAttributes ){
        detallesVentaService.deleteDetallesVenta(id);
        redirectAttributes.addFlashAttribute("exito", "el detalle de la venta fue eliminado");
        return "redirect:/detalleVenta";
    }

    @GetMapping("/buscarDetalleVenta")
    public String buscarDetalle(@RequestParam Integer id, Model model){
            DetallesVenta detallesVenta = detallesVentaService.getDetallesVentaById(id);
            model.addAttribute("detallesVenta", detallesVentaService.getAListDetallesVenta());
            model.addAttribute("detallesVentaFormu", detallesVenta);
            return "detalleVenta";
    }

    @PostMapping("/actualizarDetalleVenta/{id}")
    public String actualizarDetalle(@PathVariable Integer id, @Valid @ModelAttribute ("detallesVentaFormu") DetallesVenta detallesVenta, Model model, RedirectAttributes redirectAttributes, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("detallesVenta", detallesVentaService.getAListDetallesVenta());
            return "detalleVenta";
        }
        detallesVentaService.updateDetallesVenta(id, detallesVenta);
        redirectAttributes.addFlashAttribute("exito", "el detalle de la venta fue actualizado");
        return "redirect:/detalleVenta";

    }
}
