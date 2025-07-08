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

import cl.vasquez.recetas.dto.DiagnosticoDTO;
import cl.vasquez.recetas.service.DiagnosticoService;



@RestController
@RequestMapping("/api/recetas")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoService diagnosticoService;

    @PostMapping("/{idReceta}/diagnosticos")
    public ResponseEntity<DiagnosticoDTO> crearDiagnostico(
            @PathVariable Integer idReceta,
            @RequestBody DiagnosticoDTO diagnosticoDTO) {

        DiagnosticoDTO creado = diagnosticoService.crearDiagnostico(diagnosticoDTO, idReceta);
        return ResponseEntity.ok(creado);
    }

    // Mantienes estos para GET, PUT y DELETE
    @PutMapping("/diagnosticos/{id}")
    public DiagnosticoDTO actualizarDiagnostico(@PathVariable Integer id, @RequestBody DiagnosticoDTO diagnosticoDTO) {
        return diagnosticoService.actualizarDiagnostico(id, diagnosticoDTO);
    }

    @DeleteMapping("/diagnosticos/{id}")
    public void eliminarDiagnostico(@PathVariable Integer id) {
        diagnosticoService.eliminarDiagnostico(id);
    }

    @GetMapping("/diagnosticos")
    public List<DiagnosticoDTO> obtenerTodosDiagnosticos() {
        return diagnosticoService.obtenerTodosDiagnosticos();
    }

    @GetMapping("/diagnosticos/{id}")
    public DiagnosticoDTO obtenerDiagnosticoPorId(@PathVariable Integer id) {
        return diagnosticoService.obtenerDiagnosticoPorId(id);
    }
}

    


