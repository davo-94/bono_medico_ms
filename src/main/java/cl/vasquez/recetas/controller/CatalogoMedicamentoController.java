package cl.vasquez.recetas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.vasquez.recetas.dto.CatalogoMedicamentoDTO;
import cl.vasquez.recetas.service.CatalogoMedicamentoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/catalogo-medicamentos")
public class CatalogoMedicamentoController {

    @Autowired
    private CatalogoMedicamentoService catalogoMedicamentoService; 

    @PostMapping
    public ResponseEntity <CatalogoMedicamentoDTO> crear (@RequestBody CatalogoMedicamentoDTO dto) {
        return ResponseEntity.ok(catalogoMedicamentoService.crearCatalogoMedicamento(dto));
    }
    
    @GetMapping
    public ResponseEntity <List<CatalogoMedicamentoDTO>> obtenerTodos() {
        return ResponseEntity.ok(catalogoMedicamentoService.obtenerTodos());
    }
    

}
