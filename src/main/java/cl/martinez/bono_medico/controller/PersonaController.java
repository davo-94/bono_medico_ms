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

import cl.martinez.bono_medico.dto.PersonaDTO;
import cl.martinez.bono_medico.response.MensajeResponse;
import cl.martinez.bono_medico.service.PersonaService;

@RestController
@RequestMapping("api/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping
    public ResponseEntity<PersonaDTO> crear(@RequestBody PersonaDTO personaDTO){
        return ResponseEntity.ok(personaService.crear(personaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> obtenerPorId(@PathVariable Integer id){
        return ResponseEntity.ok(personaService.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<PersonaDTO>> obtenerTodos(){
        return ResponseEntity.ok(personaService.obtenerTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDTO> actualizar(@PathVariable Integer id, @RequestBody PersonaDTO personaDTO){
        return ResponseEntity.ok(personaService.actualizar(id, personaDTO));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeResponse> eliminar(@PathVariable Integer id){
        personaService.eliminar(id);
        return ResponseEntity.ok(new MensajeResponse("Persona eliminada con exito", true));

    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<PersonaDTO> buscarPorRut(@PathVariable Integer rut){
        return ResponseEntity.ok(personaService.buscarPorRut(rut));
    }
}
