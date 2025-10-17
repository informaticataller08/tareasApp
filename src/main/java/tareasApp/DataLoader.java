package tareasApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tareasApp.model.Usuario;
import tareasApp.service.UsuarioService;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioService usuarioService;

    public DataLoader(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Verifica si ya existe un admin
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        boolean adminExists = usuarios.stream()
                .anyMatch(u -> u.getRoles().contains("ROLE_ADMIN"));

        if (!adminExists) {
            // Crear un admin nuevo con contraseña encriptada
            usuarioService.crearUsuario("admin", "admin123", List.of("ROLE_ADMIN"));
            System.out.println("Admin inicial creado con contraseña: admin123");
        }
    }
}
