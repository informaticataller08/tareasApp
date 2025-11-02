package tareasApp.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tareasApp.service.TareaService;

import java.time.LocalDate;

@Controller
public class TareasController {

    private final TareaService tareaService;

    public TareasController(TareaService tareaService) { this.tareaService = tareaService; }

    @GetMapping("/tareas")
    public String listar(Model m,
                         @RequestParam(required=false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate desde,
                         @RequestParam(required=false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate hasta,
                         @RequestParam(required=false) String q) {
        if (q != null && !q.isBlank()) m.addAttribute("tareas", tareaService.buscar(q));
        else if (desde != null && hasta != null) m.addAttribute("tareas", tareaService.listarEntre(desde, hasta));
        else m.addAttribute("tareas", tareaService.listarHoy());
        return "tareas/lista";
    }

    @PostMapping("/tareas")
    public String crear(@RequestParam String titulo,
                        @RequestParam(required=false) String descripcion,
                        @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaVencimiento) {
        tareaService.crear(titulo, descripcion, fechaVencimiento);
        return "redirect:/tareas";
    }

    @PostMapping("/tareas/{id}/completar")
    public String completar(@PathVariable Long id) {
        tareaService.completar(id);
        return "redirect:/tareas";
    }

    @PostMapping("/tareas/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        tareaService.eliminar(id);
        return "redirect:/tareas";
    }
}
