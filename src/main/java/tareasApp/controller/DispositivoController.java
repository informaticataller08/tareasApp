package tareasApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tareasApp.model.Dispositivo;
import tareasApp.model.TipoDispositivo;
import tareasApp.service.DispositivoService;

import java.util.List;

@RestController
@RequestMapping("/api/dispositivos")
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;

    @GetMapping
    public ResponseEntity<List<Dispositivo>> listar(){
        return new ResponseEntity<>(dispositivoService.obtenerTodos(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Dispositivo> obtenerPorId(@PathVariable Long id){
        Dispositivo dispositivo=dispositivoService.obtenerPorId(id);
        return new ResponseEntity<>(dispositivo,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Dispositivo> crear(@RequestBody Dispositivo dispositivo){
        Dispositivo nuevoDispositivo = dispositivoService.guardar(dispositivo);
        return new ResponseEntity<>(nuevoDispositivo, HttpStatus.CREATED);
    }

    @GetMapping("/oficina/{nombreOficina}")
    public ResponseEntity<List<Dispositivo>> listarPorOficina(@PathVariable String nombreOficina){
        return new ResponseEntity<>(dispositivoService.obtenerPorOficina(nombreOficina),HttpStatus.OK);
    }
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Dispositivo>> listarPorTipo(@PathVariable TipoDispositivo tipo){
        return new ResponseEntity<>(dispositivoService.obtenerPorTipo(tipo),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Dispositivo> actualizar(@PathVariable Long id,@RequestBody Dispositivo detalles){
        Dispositivo actualizado=dispositivoService.actualizar(id,detalles);
        return new ResponseEntity<>(actualizado,HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        dispositivoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
