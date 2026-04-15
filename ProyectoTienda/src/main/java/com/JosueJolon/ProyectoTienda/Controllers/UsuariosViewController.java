package com.JosueJolon.ProyectoTienda.Controllers;

import com.JosueJolon.ProyectoTienda.Entity.Usuarios;
import com.JosueJolon.ProyectoTienda.Service.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuariosViewController {
    private final UsuariosService usuariosService;

    public UsuariosViewController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("usuario", usuariosService.getAListUsuarios());
        model.addAttribute("usuarioFormu", new Usuarios());
        return "usuario";
    }

    @PostMapping("/guardarUsuarios")
    public String guardarUsario(@Valid @ModelAttribute("usuarioFormu") Usuarios usuarios, Model model, RedirectAttributes redirectAttributes, BindingResult result){
        if(result.hasErrors()){
            model.addAttribute("usuario", usuariosService.getAListUsuarios());
            return "usuario";
        }
        try {
            usuariosService.saveUsuarios(usuarios);
            redirectAttributes.addFlashAttribute("excito", "el usuario fue creado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "el usuario no pudo ser creado");
        }
        return "redirect:/usuario";
    }

    @GetMapping("/editarUsuario/{id}")
    public String editarUsuario(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            model.addAttribute("usuario", usuariosService.getAListUsuarios());
            model.addAttribute("usuarioFormu", usuariosService.getUsuariosById(id));
            return "usuario";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("advertencia", "el id del usurio no existe");
            return "usuario";
        }
    }

    @PostMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        try {
            usuariosService.deleteUsuarios(id);
            redirectAttributes.addFlashAttribute("ecxito", "el usuario fue eliminado");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "no se pudo eliminar el usuario");
        }
        return "redirect:/usuario";
    }

    @GetMapping("/buscarUsuario")
    public String buscarUsuario(@RequestParam Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            Usuarios usuarios = usuariosService.getUsuariosById(id);
            model.addAttribute("usuario", usuariosService.getAListUsuarios());
            model.addAttribute("usuarioFormu", usuarios);
        }catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "Producto con ese id no existe");
        }
        return "redirect:/producto";
    }

    @PostMapping("/actualizarUsuario/{id}")
    public String actualizarUsuario(@PathVariable Integer id, @Valid @ModelAttribute ("usuarioFormu") Usuarios usuarios, Model model, RedirectAttributes redirectAttributes, BindingResult result){
        if(result.hasErrors()){
            model.addAttribute("usuarios", usuariosService.getAListUsuarios());
            model.addAttribute("usuario", usuarios );
            return "usuario";
        }
        try {
            usuariosService.updateUsuarios(id, usuarios);
            redirectAttributes.addFlashAttribute("exito", "el usuario fue actualizado");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "no se pudo actualizar el usuario");
        }
        return "redirect:/usuario";
    }

}
