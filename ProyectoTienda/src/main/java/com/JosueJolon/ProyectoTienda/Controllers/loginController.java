package com.JosueJolon.ProyectoTienda.Controllers;

import com.JosueJolon.ProyectoTienda.Entity.Productos;
import com.JosueJolon.ProyectoTienda.Entity.Usuarios;
import com.JosueJolon.ProyectoTienda.Repository.UsuariosRepository;
import com.JosueJolon.ProyectoTienda.Service.ProductosService;
import com.JosueJolon.ProyectoTienda.Service.UsuariosService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class loginController {

    private final UsuariosService usuariosService;
    private final ProductosService productosService;

    public loginController(UsuariosService usuariosService, ProductosService productosService) {

        this.usuariosService = usuariosService;
        this.productosService = productosService;
    }

    @GetMapping("/")
    public String inicio(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarLogin(){
        return "login";
    }


    @PostMapping("/login")
    public String validar(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          Model model) {

        Usuarios u = usuariosService.login(username, password);

        if (u != null) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }

    // REGISTRO
    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String guardar(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          Model model) {

        Usuarios u = usuariosService.login(username, password);

        if (u == null) {
            model.addAttribute("error", "Usuario ya existe");
            return "registro";
        }

        return "redirect:/login";
    }

    // LISTA
    @GetMapping("/lista")
    public String listar(Model model) {
        List<Usuarios> lista = usuariosService.getAListUsuarios();
        model.addAttribute("usuarios", lista);
        return "usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable  int id) {
        usuariosService.deleteUsuarios(id);
        return "redirect:/lista";
    }

    @GetMapping("/home")
    public String mostrar(HttpSession session){

        return "home";
    }

    @GetMapping("/producto")
    public String mostrarProducto(Model model){
        List<Productos> listar = productosService.getAListProductos();
        model.addAttribute( "productos", listar);
        return "producto";
    }

    @GetMapping("/detalleVenta")
    public String mostrarDetalleVenta(HttpSession session){
        return "detalleVenta";
    }

    @GetMapping("/clientes")
    public String mostrarClientes(HttpSession session){
        return "clientes";
    }

    @GetMapping("/ventas")
    public String mostrarVentas(HttpSession session){
        return "ventas";
    }

    @GetMapping("/usuario")
    public String mostrarUsuario(HttpSession session){
        return "usuario";
    }
    
}
