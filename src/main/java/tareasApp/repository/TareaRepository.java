package tareasApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tareasApp.model.Tarea;

import java.time.LocalDate;
import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {

    // usuario actual
    List<Tarea> findByUsuarioIdAndArchivadaFalse(Long usuarioId);
    List<Tarea> findByUsuarioIdAndArchivadaFalseAndFechaVencimiento(Long usuarioId, LocalDate fecha);
    List<Tarea> findByUsuarioIdAndArchivadaFalseAndFechaVencimientoBetween(Long usuarioId, LocalDate d1, LocalDate d2);
    List<Tarea> findByUsuarioIdAndArchivadaFalseAndTituloContainingIgnoreCase(Long usuarioId, String q);

    // admin global
    List<Tarea> findByArchivadaFalseAndFechaVencimiento(LocalDate fecha);
    List<Tarea> findByArchivadaFalseAndFechaVencimientoBetween(LocalDate d1, LocalDate d2);
    List<Tarea> findByArchivadaFalseAndTituloContainingIgnoreCase(String q);
}
