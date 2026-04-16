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
        usuariosService.saveUsuarios(usuarios);
        redirectAttributes.addFlashAttribute("exito", "el usuario fue creado correctamente");
        return "redirect:/usuario";
    }

    @GetMapping("/editarUsuario/{id}")
    public String editarUsuario(@PathVariable Integer id, Model model){
        model.addAttribute("usuario", usuariosService.getAListUsuarios());
        model.addAttribute("usuarioFormu", usuariosService.getUsuariosById(id));
        return "usuario";
    }

    @PostMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        usuariosService.deleteUsuarios(id);
        redirectAttributes.addFlashAttribute("exito", "el usuario fue eliminado");
        return "redirect:/usuario";
    }

    @GetMapping("/buscarUsuario")
    public String buscarUsuario(@RequestParam Integer id, Model model, RedirectAttributes redirectAttributes){
        Usuarios usuarios = usuariosService.getUsuariosById(id);
        model.addAttribute("usuario", usuariosService.getAListUsuarios());
        model.addAttribute("usuarioFormu", usuarios);
        return "redirect:/producto";
    }

    @PostMapping("/actualizarUsuario/{id}")
    public String actualizarUsuario(@PathVariable Integer id, @Valid @ModelAttribute ("usuarioFormu") Usuarios usuarios, Model model, RedirectAttributes redirectAttributes, BindingResult result){
        if(result.hasErrors()){
            model.addAttribute("usuarios", usuariosService.getAListUsuarios());
            return "usuario";
        }
        usuariosService.updateUsuarios(id, usuarios);
        redirectAttributes.addFlashAttribute("exito", "el usuario fue actualizado");
        return "redirect:/usuario";
    }

}
