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

import cl.martinez.bono_medico.dto.BonoMedicoDTO;
import cl.martinez.bono_medico.response.MensajeResponse;
import cl.martinez.bono_medico.service.BonoMedicoService;

@RestController
@RequestMapping("api/bonos")
public class BonoMedicoController {

    @Autowired
    private BonoMedicoService bonoMedicoService;

@PostMapping
public ResponseEntity<BonoMedicoDTO> crearBono(@RequestBody BonoMedicoDTO dto) {
    BonoMedicoDTO creado = bonoMedicoService.crear(dto);
    return ResponseEntity.ok(creado);
}

    @GetMapping("/{id}")
    public ResponseEntity<BonoMedicoDTO> obtenerPorId(@PathVariable Integer id){
        return ResponseEntity.ok(bonoMedicoService.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<BonoMedicoDTO>> obtenerTodos(){
        return ResponseEntity.ok(bonoMedicoService.obtenerTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BonoMedicoDTO> actualizar(@PathVariable Integer id, @RequestBody BonoMedicoDTO dto){
        return ResponseEntity.ok(bonoMedicoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeResponse> eliminar(@PathVariable Integer id){
        bonoMedicoService.eliminar(id);
        return ResponseEntity.ok(new MensajeResponse("Bono anulado exitosamente", true));
    }
}
