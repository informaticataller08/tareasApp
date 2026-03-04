package tareasApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import tareasApp.model.Dispositivo;
import tareasApp.service.DispositivoService;

public class InventarioWebController {

    @Autowired
    private DispositivoService service;

    @GetMapping
    public String verInventario(Model model){
        model.addAttribute("equipos" ,service.obtenerTodos());
        return "inventario/lista";
    }

    @PostMapping("/guardar")
    public String guardarEquipo(@ModelAttribute Dispositivo dispositivo){
        service.guardar(dispositivo);
        return "redirect:/inventario";
    }
}
