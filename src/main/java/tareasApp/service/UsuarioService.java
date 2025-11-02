package tareasApp.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tareasApp.model.Usuario;
import tareasApp.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ==== SOLO ADMIN ====

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Usuario crearUsuario(String nombre, String rawPassword, List<String> roles) {
        validarNombreUnico(nombre);
        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setPassword(passwordEncoder.encode(rawPassword));
        u.setRoles(normalizarRoles(roles));
        return usuarioRepository.save(u);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id).orElseThrow();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Usuario buscarPorNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre).orElse(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Usuario actualizar(Long id, Usuario datos, List<String> roles) {
        Usuario u = buscar(id);
        if (!u.getNombre().equals(datos.getNombre())) {
            validarNombreUnico(datos.getNombre());
        }
        u.setNombre(datos.getNombre());
        if (datos.getPassword() != null && !datos.getPassword().isBlank()) {
            u.setPassword(passwordEncoder.encode(datos.getPassword()));
        }
        u.setRoles(normalizarRoles(roles));
        return u; // JPA sincroniza al commit
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    // ==== helpers ====
    private void validarNombreUnico(String nombre) {
        if (usuarioRepository.findByNombre(nombre).isPresent()) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso");
        }
    }

    private List<String> normalizarRoles(List<String> roles) {
        return roles.stream()
                .map(r -> r.startsWith("ROLE_") ? r : "ROLE_" + r)
                .distinct()
                .toList();
    }
}
