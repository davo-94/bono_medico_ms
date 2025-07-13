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

import cl.martinez.bono_medico.dto.ProfesionalDTO;
import cl.martinez.bono_medico.response.MensajeResponse;
import cl.martinez.bono_medico.service.ProfesionalService;

@RestController
@RequestMapping("api/profesionales")
public class ProfesionalController {

    @Autowired
    private ProfesionalService profesionalService;

    @PostMapping
    public ResponseEntity<ProfesionalDTO> crear(@RequestBody ProfesionalDTO dto){
        return ResponseEntity.ok(profesionalService.crear(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesionalDTO> obtenerPorId(@PathVariable Integer id){
        return ResponseEntity.ok(profesionalService.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ProfesionalDTO>> obtenerTodos(){
        return ResponseEntity.ok(profesionalService.obtenerTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesionalDTO> actualizar(@PathVariable Integer id, @RequestBody ProfesionalDTO dto){
        return ResponseEntity.ok(profesionalService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeResponse> eliminar(@PathVariable Integer id){
        profesionalService.eliminar(id);
        return ResponseEntity.ok(new MensajeResponse("Profesional eliminado con exito", true));
    }

}
