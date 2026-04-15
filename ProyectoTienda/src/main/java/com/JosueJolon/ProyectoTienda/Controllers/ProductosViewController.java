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
        public String guardar(@Valid @ModelAttribute("productoForm") Productos p, BindingResult result, Model model, RedirectAttributes ra) {
            if (result.hasErrors()) {
                model.addAttribute("productos", productosService.getAListProductos());
                return "producto";
            }

            try {
                productosService.saveProductos(p);
                ra.addFlashAttribute("exito", "el producto fue creado.");
            } catch (Exception e) {
                ra.addFlashAttribute("error", "Error al crear: " + e.getMessage());
            }

            return "redirect:/producto";
        }
        @GetMapping("/editar/{id}")
        public String editar(@PathVariable Integer id, Model model, RedirectAttributes ra) {
            try {
                model.addAttribute("productos", productosService.getAListProductos());
                model.addAttribute("productoForm", productosService.getProductosById(id));
                return "producto";
            } catch (Exception e) {
                ra.addFlashAttribute("msg", "Producto no encontrado");
                return "producto";
            }
        }

        @PostMapping("/eliminar/{id}")
        public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
            try {
                productosService.deleteProductos(id);
                ra.addFlashAttribute("msg", "Producto eliminado");
            } catch (Exception e) {
                ra.addFlashAttribute("msg", "Error al eliminar");
            }
            return "redirect:/producto";
        }

        @GetMapping("/buscar")
        public String buscar(@RequestParam Integer id, Model model, RedirectAttributes ra) {
            try {
                Productos encontrado = productosService.getProductosById(id);
                model.addAttribute("productos", productosService.getAListProductos());
                model.addAttribute("productoForm", encontrado);
                return "producto";
            } catch (Exception e) {
                ra.addFlashAttribute("msg", "Producto con ese id no existe");
                return "redirect:/producto";
            }
        }

        @PostMapping("/actualizar/{id}")
        public String actualizar(@PathVariable Integer id, @Valid @ModelAttribute("productoForm") Productos p, BindingResult result, Model model, RedirectAttributes ra) {

            if (result.hasErrors()) {
                model.addAttribute("productos", productosService.getAListProductos());
                model.addAttribute("producto", p);
                return "producto";
            }
            try {
                productosService.updateProductos(id, p);
                ra.addFlashAttribute("exito", "Producto actualizado correctamente.");
            } catch (Exception e) {
                ra.addFlashAttribute("error", " Error al actualizar: " + e.getMessage());
            }
            return "redirect:/producto";
        }
    }




