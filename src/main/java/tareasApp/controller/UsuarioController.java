package tareasApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tareasApp.model.Usuario;
import tareasApp.service.UsuarioService;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@Controller
@RequestMapping("/admin/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService s){ this.usuarioService = s; }

    @GetMapping
    public String listar(Model m){ m.addAttribute("usuarios", usuarioService.listarUsuarios()); return "admin/usuarios"; }

    @GetMapping("/nuevo")
    public String nuevo(Model m){ m.addAttribute("usuario", new Usuario()); return "admin/usuario_form"; }

    @PostMapping("/nuevo")
    public String crear(@ModelAttribute Usuario u, @RequestParam(required=false) boolean esAdmin){
        usuarioService.crearUsuario(u.getNombre(), u.getPassword(), esAdmin? List.of("ROLE_ADMIN"): List.of("ROLE_USER"));
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Long id, Model m){
        m.addAttribute("usuario", usuarioService.buscar(id));
        return "admin/usuario_form";
    }

    @PostMapping("/{id}/editar")
    public String editar(@PathVariable Long id, @ModelAttribute Usuario u, @RequestParam(required=false) boolean esAdmin){
        usuarioService.actualizar(id, u, esAdmin? List.of("ROLE_ADMIN"): List.of("ROLE_USER"));
        return "redirect:/admin/usuarios";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id){
        usuarioService.eliminar(id);
        return "redirect:/admin/usuarios";
    }
}
