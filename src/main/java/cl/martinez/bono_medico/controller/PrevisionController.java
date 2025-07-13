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

import cl.martinez.bono_medico.dto.PrevisionDTO;
import cl.martinez.bono_medico.response.MensajeResponse;
import cl.martinez.bono_medico.service.PrevisionService;

@RestController
@RequestMapping("api/previsiones")
public class PrevisionController {

    @Autowired
    private PrevisionService previsionService;

    @PostMapping 
    public ResponseEntity<PrevisionDTO> crear(@RequestBody PrevisionDTO dto){
        return ResponseEntity.ok(previsionService.crear(dto)); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrevisionDTO> obtenerPorId(@PathVariable Integer id){
        return ResponseEntity.ok(previsionService.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<PrevisionDTO>> obtenerTodos(){
        return ResponseEntity.ok(previsionService.obtenerTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrevisionDTO> actualizar(@PathVariable Integer id, @RequestBody PrevisionDTO dto){
        return ResponseEntity.ok(previsionService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeResponse> eliminar(@PathVariable Integer id){
        previsionService.eliminar(id);
        return ResponseEntity.ok(new MensajeResponse("Prevision eliminada con exito", true));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<PrevisionDTO>> crearPrevisiones(@RequestBody List<PrevisionDTO> previsiones){
        return ResponseEntity.ok(previsionService.crearMultiples(previsiones));
    }

}
