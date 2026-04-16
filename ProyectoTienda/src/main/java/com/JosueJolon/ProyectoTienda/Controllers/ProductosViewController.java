package com.JosueJolon.ProyectoTienda.Controllers;

import com.JosueJolon.ProyectoTienda.Entity.Productos;
import com.JosueJolon.ProyectoTienda.Service.ProductosService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/producto")
public class ProductosViewController {
        private final ProductosService productosService;

        public ProductosViewController(ProductosService service) {
            this.productosService = service;
        }

        @GetMapping
        public String listar(Model model) {
            model.addAttribute("productos", productosService.getAListProductos());
            model.addAttribute("productoForm", new Productos());
            return "producto";
        }

        @PostMapping("/guardar")
        public String guardar(@Valid @ModelAttribute("productoForm") Productos p, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
            if (result.hasErrors()) {
                model.addAttribute("productos", productosService.getAListProductos());
                return "producto";
            }
            productosService.saveProductos(p);
            redirectAttributes.addFlashAttribute("exito", "el producto fue creado.");
            return "redirect:/producto";
        }
        @GetMapping("/editar/{id}")
        public String editar(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
            model.addAttribute("productos", productosService.getAListProductos());
            model.addAttribute("productoForm", productosService.getProductosById(id));
            return "producto";

        }

        @PostMapping("/eliminar/{id}")
        public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
            productosService.deleteProductos(id);
            redirectAttributes.addFlashAttribute("exito", "Producto eliminado");
            return "redirect:/producto";

        }

        @GetMapping("/buscar")
        public String buscar(@RequestParam Integer id, Model model) {
                Productos encontrado = productosService.getProductosById(id);
                model.addAttribute("productos", productosService.getAListProductos());
                model.addAttribute("productoForm", encontrado);
                return "producto";
        }

        @PostMapping("/actualizar/{id}")
        public String actualizar(@PathVariable Integer id, @Valid @ModelAttribute("productoForm") Productos p, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

            if (result.hasErrors()) {
                model.addAttribute("productos", productosService.getAListProductos());
                return "producto";
            }
            productosService.updateProductos(id, p);
            redirectAttributes.addFlashAttribute("exito", "Producto actualizado correctamente.");
            return "redirect:/producto";
        }
    }




