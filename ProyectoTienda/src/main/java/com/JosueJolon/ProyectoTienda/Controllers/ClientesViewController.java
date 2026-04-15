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
    public String guardar(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes){
        try{
            model.addAttribute("clientes", clientesService.getAListClientes());
            model.addAttribute("clienteFormu", clientesService.getClientesById(id));
            return "clientes";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "no se encontro el id del cliente");
            return "clientes";
        }
    }

    @PostMapping("/guardarCliente")
    public String guardarClientes(@Valid @ModelAttribute ("clientesFormu") Clientes clientes, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if(result.hasErrors()){
            model.addAttribute("cliente", clientesService.getAListClientes());
            return "clientes";
        }

        try {
            clientesService.saveClientes(clientes);
            redirectAttributes.addFlashAttribute("exito", "el cliente fue creado");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "error al crear el cliente");
        }
        return "redirect:/clientes";
    }

    @GetMapping("/buscarCliente")
    public String buscarCliente(@RequestParam Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            Clientes clientes = clientesService.getClientesById(id);
            model.addAttribute("cliente", clientesService.getAListClientes());
            model.addAttribute("clienteFormu", clientes);
            return "clientes";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("advertencia", "el id del cliente no existe");
            return "redirect:/cliente";
        }
    }

    @PostMapping("/actualizarClientes/{id}")
    public String actualizarCliente(@PathVariable Integer id, @Valid @ModelAttribute ("clientesFormu") Clientes clientes, Model model, RedirectAttributes redirectAttributes, BindingResult result){
       if (result.hasErrors()) {
           model.addAttribute("cliente", clientesService.getAListClientes());
           model.addAttribute("cliente", clientes);
           return "cliente";
       }
       try {
           clientesService.updateClientes(id, clientes);
           redirectAttributes.addFlashAttribute("ecito", "el cliente se ha actualizado");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "el cliente no se pudo actualizar");
        }
        return "cliente";
    }

    @PostMapping("/eliminarClientes/{id}")
    public String eliminarCliente(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        try {
            clientesService.deleteClientes(id);
            redirectAttributes.addFlashAttribute("exito", "el cliente se ha eliminado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "el id del cliente no existe");
        }
        return "redirect:/cliente";
    }
}
