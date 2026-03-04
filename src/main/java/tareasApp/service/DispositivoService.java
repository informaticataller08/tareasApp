package tareasApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tareasApp.model.Dispositivo;
import tareasApp.model.TipoDispositivo;
import tareasApp.repository.DispositivoRepository;

import java.util.List;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    public List<Dispositivo> obtenerTodos(){
        return dispositivoRepository.findAll();
    }

    public Dispositivo guardar(Dispositivo dispositivo){
        return dispositivoRepository.save(dispositivo);
    }

    public Dispositivo actualizar(Long id, Dispositivo detallesNuevos){
        Dispositivo dispositivoExistente = obtenerPorId(id);

        dispositivoExistente.setTipo(detallesNuevos.getTipo());
        dispositivoExistente.setOficina(detallesNuevos.getOficina());
        dispositivoExistente.setDestino(detallesNuevos.getDestino());
        dispositivoExistente.setDescripcion(detallesNuevos.getDescripcion());

        return dispositivoRepository.save(dispositivoExistente);
    }

    public void eliminar(Long id){
        dispositivoRepository.deleteById(id);
    }
    public List<Dispositivo> obtenerPorOficina(String oficina){
        return dispositivoRepository.findByOficina(oficina);
    }
    public List<Dispositivo> obtenerPorTipo(TipoDispositivo tipo){
        return dispositivoRepository.findByTipo(tipo);
    }
    public Dispositivo obtenerPorId(Long id){
        return dispositivoRepository.findById(id).orElseThrow(() -> new RuntimeException("Dispositivo no encontrado"));
    }
}
