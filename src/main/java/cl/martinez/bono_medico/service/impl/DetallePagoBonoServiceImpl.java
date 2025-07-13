package cl.martinez.bono_medico.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.martinez.bono_medico.dto.DetallePagoBonoDTO;
import cl.martinez.bono_medico.exception.ResourceNotFoundException;
import cl.martinez.bono_medico.model.BonoMedico;
import cl.martinez.bono_medico.model.DetallePagoBono;
import cl.martinez.bono_medico.repository.BonoMedicoRepository;
import cl.martinez.bono_medico.repository.DetallePagoBonoRepository;
import cl.martinez.bono_medico.service.DetallePagoBonoService;

@Service
public class DetallePagoBonoServiceImpl implements DetallePagoBonoService{

    @Autowired
    private DetallePagoBonoRepository repository;

    @Autowired
    private BonoMedicoRepository bonoRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public DetallePagoBonoDTO crear(DetallePagoBonoDTO dto) {
        String medio = dto.getMedioPago().toLowerCase();
        if (!List.of("efectivo", "debito", "credito").contains(medio)) {
            throw new IllegalArgumentException("Medio de pago no válido");
        }

        BonoMedico bono = bonoRepository.findById(dto.getIdBono())
            .orElseThrow(() -> new ResourceNotFoundException("Bono no encontrado"));

        if (repository.findByBono_IdBono(bono.getIdBono()).isPresent()) {
            throw new IllegalStateException("Ya existe un detalle para este bono");
        }

        DetallePagoBono detalle = new DetallePagoBono();
        detalle.setBono(bono);
        detalle.setMedioPago(medio);
        detalle.setMontoPagado(bono.getPrecio_total());

        // Fecha como String
        LocalDate hoy = LocalDate.now();
        detalle.setFechaPago(hoy.toString());

        return mapper.map(repository.save(detalle), DetallePagoBonoDTO.class);
    }

    @Override
    public DetallePagoBonoDTO obtenerPorIdBono(Integer idBono) {
        DetallePagoBono detalle = repository.findByBono_IdBono(idBono)
            .orElseThrow(() -> new ResourceNotFoundException("Detalle no encontrado"));
        return mapper.map(detalle, DetallePagoBonoDTO.class);
    }
    
    
}
