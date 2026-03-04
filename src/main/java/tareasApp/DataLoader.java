package tareasApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tareasApp.model.Usuario;
import tareasApp.repository.UsuarioRepository;

import java.util.Collections; // para singletonList en Java 8
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository repo;
    private final PasswordEncoder encoder;

    public DataLoader(UsuarioRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        // evita NPE si roles es null
        boolean adminExists = repo.findAll().stream().anyMatch(u -> {
            List<String> roles = u.getRoles();
            if (roles == null) return false;
            return roles.stream().anyMatch("ROLE_ADMIN"::equals);
        });

        if (!adminExists) {
            Usuario u = new Usuario();
            u.setNombre("admin");
            u.setPassword(encoder.encode("admin123"));
            // usa Collections.singletonList para Java 8
            u.setRoles(Collections.singletonList("ROLE_ADMIN"));
            repo.save(u);
            System.out.println("Admin inicial creado. Cambiar contraseña.");
        }
    }
}
