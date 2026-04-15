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
    public String listar(Model model){
        model.addAttribute("ventas", ventasService.getAListVentas());
        model.addAttribute("ventaFormu", new Ventas());
        return "ventas";
    }

    @PostMapping("/guardarVenta")
    public String guardarVenta(@Valid @ModelAttribute ("ventaFormu") Ventas ventas, Model model, RedirectAttributes redirectAttributes, BindingResult result){
        if(result.hasErrors()){
            model.addAttribute("ventas", ventasService.getAListVentas());
            return "ventas";
        }
        try {
            ventasService.saveVentas(ventas);
            redirectAttributes.addFlashAttribute("exito", "la venta fue creada");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", "error al crear la venta");
        }
        return "redirect:/ventas";
    }

    @GetMapping("/editarVenta/{id}")
    public String editarVenta(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            model.addAttribute("ventas", ventasService.getAListVentas());
            model.addAttribute("ventasFormu", ventasService.getVentasById(id));
            return "ventas";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("advertencia", "la venta no fue encontrada");
            return "ventas";
        }
    }

    @PostMapping("/eliminarVenta/{id}")
    public String eliminarVenta(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        try {
            ventasService.deleteVentas(id);
            redirectAttributes.addFlashAttribute("exito", "la venta fue eliminada");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "error al eliminar la venta");
        }
        return "redirect:/ventas";
    }

    @GetMapping("/buscarVenta")
    public String buscarVenta (@RequestParam Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            Ventas ventas = ventasService.getVentasById(id);
            model.addAttribute("ventas", ventasService.getAListVentas());
            model.addAttribute("ventasFormu", ventas);
            return "ventas";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("advertencia", "la venta con ese id no existe");
            return "";
        }
    }

    @PostMapping("/actualizarVentas/{id}")
    public String actualizarVenta(@PathVariable Integer id, @Valid @ModelAttribute("ventasFormu") Ventas ventas, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if(result.hasErrors()){
            model.addAttribute("ventas", ventasService.getAListVentas());
            model.addAttribute("venta", ventas);
            return "venta";
        }
        try {
            ventasService.updateVentas(id, ventas);
            redirectAttributes.addFlashAttribute("exito", "venta actualizada correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "error al actualizar la venta");
        }
        return "redirect:/ventas";
    }
}

