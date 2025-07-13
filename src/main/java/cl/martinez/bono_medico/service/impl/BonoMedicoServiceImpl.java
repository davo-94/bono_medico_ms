package cl.martinez.bono_medico.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.martinez.bono_medico.dto.BonoMedicoDTO;
import cl.martinez.bono_medico.exception.ResourceNotFoundException;
import cl.martinez.bono_medico.mapper.BonoMedicoMapper;
import cl.martinez.bono_medico.model.BonoMedico;
import cl.martinez.bono_medico.model.Persona;
import cl.martinez.bono_medico.model.Profesional;
import cl.martinez.bono_medico.repository.BonoMedicoRepository;
import cl.martinez.bono_medico.repository.PersonaRepository;
import cl.martinez.bono_medico.repository.ProfesionalRepository;
import cl.martinez.bono_medico.service.BonoMedicoService;
import jakarta.transaction.Transactional;

@Service
public class BonoMedicoServiceImpl implements BonoMedicoService{

    @Autowired
    private BonoMedicoRepository bonoRepository;

    @Autowired
    private BonoMedicoMapper mapper;

    @Autowired
private PersonaRepository personaRepository;

@Autowired
private ProfesionalRepository profesionalRepository;

@Override
@Transactional
public BonoMedicoDTO crear(BonoMedicoDTO dto) {
    BonoMedico bono = new BonoMedico();

    // Cargar persona y profesional desde BDD
    Persona persona = personaRepository.findById(dto.getIdPersona())
        .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + dto.getIdPersona()));

    Profesional profesional = profesionalRepository.findById(dto.getIdProfesional())
        .orElseThrow(() -> new RuntimeException("Profesional no encontrado con ID: " + dto.getIdProfesional()));

    // Asignar relaciones
    bono.setPersona(persona);
    bono.setProfesional(profesional);

    // Fechas
    LocalDate hoy = LocalDate.now();
    bono.setFechaEmision(hoy.toString());
    bono.setFechaVencimiento(hoy.plusDays(1).toString());

    // Estado
    bono.setEstado("Vigente");

    // Precio base según previsión
    Integer idPrevision = persona.getPrevision().getIdPrevision();
    int precioBase;
    if (idPrevision == 1) {
        precioBase = 3000; // Fonasa
    } else if (idPrevision == 2) {
        precioBase = 10000; // Isapre
    } else {
        precioBase = 5000; // Default
    }

    // Obtener la especialidad del profesional
    String especialidad = profesional.getEspecialidad().getNombre();

    // Si la especialidad no es Medicina General, aplicar incremento
    double incremento = 0.0;
    if (!"Medicina General".equals(especialidad)) {
        if (idPrevision == 1) {
            incremento = 0.05; // Fonasa 5% incremento
        } else if (idPrevision == 2) {
            incremento = 0.15; // Isapre 15% incremento
        }
    }

    // Calcular el precio total con el incremento
    int precioTotal = (int) Math.round(precioBase * (1 + incremento));
    bono.setPrecio_total(precioTotal);

    // Guardar y devolver
    BonoMedico bonoGuardado = bonoRepository.save(bono);
    return mapper.toDTO(bonoGuardado);
}

    @Override
    public BonoMedicoDTO obtenerPorId(Integer id) {
        return mapper.toDTO(
            bonoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Bono no encontrado con ID: "+ id))
        );
    }

    @Override
    public List<BonoMedicoDTO> obtenerTodos() {
        return bonoRepository.findAll()
            .stream()
            .map(mapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public BonoMedicoDTO actualizar(Integer id, BonoMedicoDTO dto) {
        BonoMedico existente = bonoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Bono no encontrado con ID: "+id));

        existente.setEstado(dto.getEstado());
        // No se actualiza persona, profesional ni prevision para evitar conflictos
        return mapper.toDTO(bonoRepository.save(existente));
    }

    @Override
    public void eliminar(Integer id) {
        BonoMedico bonoMedico = bonoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Bono no encontrado con ID: "+id));
        bonoRepository.delete(bonoMedico);
    }


}
