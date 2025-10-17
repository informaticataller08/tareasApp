package tareasApp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test-roles")
    public String verRoles(Model model) {
        // Obtenemos el usuario autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // nombre de usuario
        Object roles = auth.getAuthorities(); // roles

        model.addAttribute("username", username);
        model.addAttribute("roles", roles);

        return "test_roles"; // Thymeleaf template
    }
}
