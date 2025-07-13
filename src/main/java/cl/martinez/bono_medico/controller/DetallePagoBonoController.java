package cl.martinez.bono_medico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.martinez.bono_medico.dto.DetallePagoBonoDTO;
import cl.martinez.bono_medico.service.DetallePagoBonoService;

@RestController
@RequestMapping("api/detalle-pago")
public class DetallePagoBonoController {

    @Autowired
    private DetallePagoBonoService service;

    @PostMapping
    public ResponseEntity<DetallePagoBonoDTO> crear(@RequestBody DetallePagoBonoDTO dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @GetMapping("/bono/{idBono}")
    public ResponseEntity<DetallePagoBonoDTO> obtener(@PathVariable Integer idBono) {
        return ResponseEntity.ok(service.obtenerPorIdBono(idBono));
    }
}
