package tareasApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tareasApp.model.Usuario; // Asegurate de tener esta clase en tu paquete model

@Controller
public class TareasController {

    @GetMapping("/tareas")
    public String listarTareas(Model model) {
        // Creamos un nuevo objeto Usuario para el formulario
        model.addAttribute("usuario", new Usuario());

        // Retornamos el template para admins
        return "admin/usuario_form";
    }
}
