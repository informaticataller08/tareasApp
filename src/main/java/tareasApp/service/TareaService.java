package tareasApp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tareasApp.model.Tarea;
import tareasApp.model.Usuario;
import tareasApp.repository.TareaRepository;

import java.util.List;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @Transactional(readOnly = true)
    public List<Tarea> listarPorUsuario(Usuario usuario) {
        if (usuario == null || usuario.getId() == null) {
            return List.of();
        }
        return tareaRepository.findByUsuarioId(usuario.getId());
    }

    @Transactional(readOnly = true)
    public List<Tarea> listarPendientesPorUsuario(Usuario usuario) {
        if (usuario == null || usuario.getId() == null) {
            return List.of();
        }
        return tareaRepository.findByUsuarioIdAndCompletadaFalse(usuario.getId());
    }
}