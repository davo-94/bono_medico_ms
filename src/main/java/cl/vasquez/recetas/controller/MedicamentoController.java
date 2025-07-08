package cl.vasquez.recetas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.vasquez.recetas.dto.MedicamentoDTO;
import cl.vasquez.recetas.service.MedicamentoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/recetas/{idReceta}/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @PostMapping
    public ResponseEntity<MedicamentoDTO> crearMedicamento(
            @PathVariable Integer idReceta,
            @RequestBody MedicamentoDTO medicamentoDTO) {

        MedicamentoDTO creado = medicamentoService.crearMedicamento(medicamentoDTO, idReceta);
        return ResponseEntity.ok(creado);
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoDTO>> obtenerMedicamentosPorReceta(
            @PathVariable Integer idReceta) {

        List<MedicamentoDTO> medicamentos = medicamentoService.obtenerMedicamentosPorReceta(idReceta);
        return ResponseEntity.ok(medicamentos);
    }
}

