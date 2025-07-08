package cl.vasquez.recetas.controller;

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

import cl.vasquez.recetas.dto.EstadoDTO;
import cl.vasquez.recetas.dto.RecetaDTO;
import cl.vasquez.recetas.service.RecetaService;

@RestController //Esta clase es un controlador REST -> Respuestas JSON
@RequestMapping("/api/recetas") //Todas las rutas de este controller comienzan con /api/recetas
public class RecetaController {

    @Autowired //Inyección del servicio que maneja la lógica de negocio 
    private RecetaService recetaService; //El controller no tiene lógica de negocio, solo delega

    @PostMapping //Recibe un JSON
    public ResponseEntity<RecetaDTO> crearReceta (@RequestBody RecetaDTO recetaDTO){ 
        return ResponseEntity.ok(recetaService.crearReceta(recetaDTO)); //Llama al recetaService para guardar. Devuelve la receta en un ResponseEntity.
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<RecetaDTO> obtenerPorId(@PathVariable Integer id){
        return ResponseEntity.ok(recetaService.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity <List<RecetaDTO>> obtenerTodas() {
        return ResponseEntity.ok(recetaService.obtenerTodas());
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<RecetaDTO> actualizarEstado(
    @PathVariable Integer id, 
    @RequestBody EstadoDTO estadoDTO) {

    RecetaDTO recetaActualizada = recetaService.actualizarEstado(id, estadoDTO.getEstado());
    return ResponseEntity.ok(recetaActualizada);
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReceta(@PathVariable Integer id) {
        recetaService.eliminarReceta(id);
        return ResponseEntity.noContent().build();
    }
}

