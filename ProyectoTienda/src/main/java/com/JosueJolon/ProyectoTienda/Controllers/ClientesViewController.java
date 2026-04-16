package com.JosueJolon.ProyectoTienda.Controllers;

import com.JosueJolon.ProyectoTienda.Entity.Clientes;
import com.JosueJolon.ProyectoTienda.Service.ClientesService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clientes")
public class ClientesViewController {
    private final ClientesService clientesService;

    public ClientesViewController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("clientes", clientesService.getAListClientes());
        model.addAttribute("clienteFormu", new Clientes());
        return "clientes";
    }

    @GetMapping("/editarCliente/{id}")
    public String guardar(@PathVariable Integer id, Model model){
            model.addAttribute("clientes", clientesService.getAListClientes());
            model.addAttribute("clienteFormu", clientesService.getClientesById(id));
            return "clientes";
    }

    @PostMapping("/guardarCliente")
    public String guardarClientes(@Valid @ModelAttribute ("clientesFormu") Clientes clientes, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if(result.hasErrors()){
            model.addAttribute("clientes", clientesService.getAListClientes());
            return "clientes";
        }
        clientesService.saveClientes(clientes);
        redirectAttributes.addFlashAttribute("exito", "el cliente fue creado");
        return "redirect:/clientes";
    }

    @GetMapping("/buscarCliente")
    public String buscarCliente(@RequestParam Integer id, Model model){
            Clientes clientes = clientesService.getClientesById(id);
            model.addAttribute("clientes", clientesService.getAListClientes());
            model.addAttribute("clienteFormu", clientes);
            return "clientes";
    }

    @PostMapping("/actualizarClientes/{id}")
    public String actualizarCliente(@PathVariable Integer id, @Valid @ModelAttribute ("clientesFormu") Clientes clientes, Model model, RedirectAttributes redirectAttributes, BindingResult result){
       if (result.hasErrors()) {
           model.addAttribute("cliente", clientesService.getAListClientes());
           return "clientes";
       }
       clientesService.updateClientes(id, clientes);
       redirectAttributes.addFlashAttribute("ecito", "el cliente se ha actualizado");
        return "redirect:/clientes";
    }

    @PostMapping("/eliminarClientes/{id}")
    public String eliminarCliente(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        clientesService.deleteClientes(id);
        redirectAttributes.addFlashAttribute("exito", "el cliente se ha eliminado correctamente");
        return "redirect:/clientes";
    }
}
