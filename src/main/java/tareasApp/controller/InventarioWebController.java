package tareasApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tareasApp.model.Dispositivo;
import tareasApp.service.DispositivoService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/inventario")
public class InventarioWebController {

    @Autowired
    private DispositivoService service;

    @GetMapping
    public String verInventario(Model model){
        model.addAttribute("equipos" ,service.obtenerTodos());
        return "inventario/listaInventario";
    }

    @PostMapping("/guardar")
    public String guardarEquipo(@ModelAttribute Dispositivo dispositivo){
        service.guardar(dispositivo);
        return "redirect:/inventario";
    }
    @PostMapping("/eliminar/{id}")
    public String eliminarEquipo(@PathVariable Long id){
        service.eliminar(id);
        return "redirect:/inventario";
    }
    @GetMapping("/editar/{id}")
    @ResponseBody
    public Dispositivo obtenerParaEditar(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }
}
