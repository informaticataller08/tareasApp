package tareasApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tareasApp.model.Usuario;
import tareasApp.service.UsuarioService;

import java.util.List;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Listar usuarios
    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "admin/usuarios"; // Thymeleaf template
    }

    // Formulario de creación de usuario
    @GetMapping("/nuevo")
    public String nuevoUsuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "admin/usuario_form";
    }

    // Procesar creación de usuario
    @PostMapping("/nuevo")
    public String crearUsuario(@ModelAttribute Usuario usuario, @RequestParam(required = false) boolean esAdmin) {
        List<String> roles = esAdmin ? List.of("ROLE_ADMIN") : List.of("ROLE_USER");
        usuarioService.crearUsuario(usuario.getNombre(), usuario.getPassword(), roles);
        return "redirect:/admin/usuarios";
    }
}
