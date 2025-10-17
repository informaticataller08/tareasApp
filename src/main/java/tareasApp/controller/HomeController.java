package tareasApp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Authentication authentication) {
        if (authentication != null) {
            boolean isAdmin = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch(role -> role.equals("ROLE_ADMIN"));

            if (isAdmin) {
                return "redirect:/admin/usuarios"; // Admin va a la lista de usuarios
            } else {
                return "redirect:/tareas"; // Usuarios normales van a tareas
            }
        }
        return "redirect:/login"; // No logueado → login
    }
}
