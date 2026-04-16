package com.JosueJolon.ProyectoTienda.Controllers;

import com.JosueJolon.ProyectoTienda.Entity.Ventas;
import com.JosueJolon.ProyectoTienda.Service.VentasService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ventas")
public class VentasViewController {
    private final VentasService ventasService;

    public VentasViewController(VentasService ventasService) {
        this.ventasService = ventasService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ventas", ventasService.getAListVentas());
        model.addAttribute("ventaFormu", new Ventas());
        return "ventas";
    }

    @PostMapping("/guardarVenta")
    public String guardarVenta(@Valid @ModelAttribute("ventaFormu") Ventas ventas, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("ventas", ventasService.getAListVentas());
            return "ventas";
        }
        ventasService.saveVentas(ventas);
        redirectAttributes.addFlashAttribute("exito", "la venta fue creada");
        return "redirect:/ventas";
    }

    @GetMapping("/editarVenta/{id}")
    public String editarVenta(@PathVariable Integer id, Model model) {
        model.addAttribute("ventas", ventasService.getAListVentas());
        model.addAttribute("ventaFormu", ventasService.getVentasById(id));
        return "ventas";
    }

    @PostMapping("/eliminarVenta/{id}")
    public String eliminarVenta(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        ventasService.deleteVentas(id);
        redirectAttributes.addFlashAttribute("exito", "la venta fue eliminada");
        return "redirect:/ventas";
    }

    @GetMapping("/buscarVenta")
    public String buscarVenta(@RequestParam Integer id, Model model) {
        Ventas ventas = ventasService.getVentasById(id);
        model.addAttribute("ventas", ventasService.getAListVentas());
        model.addAttribute("ventaFormu", ventas);
        return "ventas";
    }

    @PostMapping("/actualizarVentas/{id}")
    public String actualizarVenta(@PathVariable Integer id, @Valid @ModelAttribute("ventaFormu") Ventas ventas, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("ventas", ventasService.getAListVentas());
            return "ventas";
        }
        ventasService.updateVentas(id, ventas);
        redirectAttributes.addFlashAttribute("exito", "venta actualizada correctamente");
        return "redirect:/ventas";
    }
}