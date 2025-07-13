package cl.martinez.bono_medico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.martinez.bono_medico.dto.EspecialidadDTO;
import cl.martinez.bono_medico.response.MensajeResponse;
import cl.martinez.bono_medico.service.EspecialidadService;

@RestController
@RequestMapping("api/especialidades")
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    @PostMapping
    public ResponseEntity<EspecialidadDTO> crear(@RequestBody EspecialidadDTO dto){
        return ResponseEntity.ok(especialidadService.crear(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadDTO> obtenerPorId(@PathVariable Integer id){
        return ResponseEntity.ok(especialidadService.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<EspecialidadDTO>> obtenerTodos(){
        return ResponseEntity.ok(especialidadService.obtenerTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadDTO> actualizar(@PathVariable Integer id, @RequestBody EspecialidadDTO dto){
        return ResponseEntity.ok(especialidadService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeResponse> eliminar(@PathVariable Integer id){
        especialidadService.eliminar(id);
        return ResponseEntity.ok(new MensajeResponse("Especialidad eliminada con exito", true));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<EspecialidadDTO>> crearEspecialidades(@RequestBody List<EspecialidadDTO> especialidades){
        return ResponseEntity.ok(especialidadService.crearMultiples(especialidades));
    }

}
