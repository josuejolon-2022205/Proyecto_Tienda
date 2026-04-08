package com.JosueJolon.ProyectoTienda.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class loginController {
    @GetMapping("/")
    public String inicio(){
        return "redirect:/usuario";
    }

    @GetMapping("/usuario")
    public String mostrarLogin(){
        return "usuario";
    }

    @PostMapping("/login")
    public String login(@RequestParam String usuario, @RequestParam String password, HttpSession session, Model model){
        String userCorrecto = "admin";
        String passCorrecto = "1234";

        if(usuario.equals(userCorrecto) && password.equals(passCorrecto)){
            session.setAttribute("usuarioLogueado", usuario);
            return "redirect:/home";
        }else{
            model.addAttribute("error", "usuario y contraseña incorrectos");
            return "usuario";
        }
    }

    @GetMapping("/home")
    public String mostrar(HttpSession session){

        if(session.getAttribute("usuarioLogueado") == null){
            return "redirect:/usuario";
        }
        return "home";
    }

    @GetMapping("/producto")
    public String mostrarProducto(HttpSession session){
        if(session.getAttribute("usuarioLogueado") == null){
            return "redirect:/usuario";
        }
        return "producto";
    }

    @GetMapping("/detalleVenta")
    public String mostrarDetalleVenta(HttpSession session){
        if(session.getAttribute("usuarioLogueado") == null){
            return "redirect:/usuario";
        }
        return "detalleVenta";
    }

    @GetMapping("/clientes")
    public String mostrarClientes(HttpSession session){
        if (session.getAttribute("usuarioLogueado") == null){
            return "redirect:/usuario";
        }
        return "clientes";
    }
    
}
