package tareasApp.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    // Crear usuario con contraseña encriptada y lista de roles
    public Usuario crearUsuario(String nombre, String rawPassword, List<String> roles) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setPassword(passwordEncoder.encode(rawPassword)); // encriptar
        usuario.setRoles(roles);
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    // Buscar usuario por nombre (opcional, útil para login o validaciones)
    public Usuario buscarPorNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre).orElse(null);
    }
}
