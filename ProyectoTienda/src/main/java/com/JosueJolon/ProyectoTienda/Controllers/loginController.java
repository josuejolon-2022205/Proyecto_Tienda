package com.JosueJolon.ProyectoTienda.Controllers;

import com.JosueJolon.ProyectoTienda.Entity.*;
import com.JosueJolon.ProyectoTienda.Service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class loginController {

    private final UsuariosService usuariosService;

    public loginController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }


    @PostMapping("/login")
    public String validar(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession httpSession) {

        Usuarios u = usuariosService.login(username, password);

        if (u != null) {
            httpSession.setAttribute("usuarioNombre", u.getUsername());
            httpSession.setAttribute("usuarioRol", u.getRol());
            httpSession.setAttribute("usuarioId", u.getCodigo_usuario());
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }


    @GetMapping("/home")
    public String mostrar(HttpSession session, Model model) {
        model.addAttribute("username", session.getAttribute("usuarioNombre"));
        model.addAttribute("rol", session.getAttribute("usuarioRol"));

        return "home";
    }


    // REGISTRO
    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String guardar(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email, @RequestParam("rol") String rol, @RequestParam(value = "estado", defaultValue = "1") Integer estado, Model model, RedirectAttributes redirectAttributes) {
        try {
            Usuarios nuevo = new Usuarios();
            nuevo.setUsername(username);
            nuevo.setPassword(password);
            nuevo.setEmail(email);
            nuevo.setRol(rol);
            nuevo.setEstado(estado);

            usuariosService.saveUsuarios(nuevo);
            redirectAttributes.addFlashAttribute("exito", "Usuario registrado correctamente. Inicia sesión.");
            return "redirect:/login";

        } catch (Exception e) {
            model.addAttribute("error", "Error al registrar: " + e.getMessage());
            return "registro";
        }
    }


    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }


    @GetMapping("/lista")
    public String listar(Model model) {
        List<Usuarios> lista = usuariosService.getAListUsuarios();
        model.addAttribute("usuarios", lista);
        return "usuario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        usuariosService.deleteUsuarios(id);
        return "redirect:/lista";
    }

}

