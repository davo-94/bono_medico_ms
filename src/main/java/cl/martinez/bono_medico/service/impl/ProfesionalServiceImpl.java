package cl.martinez.bono_medico.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.martinez.bono_medico.dto.ProfesionalDTO;
import cl.martinez.bono_medico.exception.ResourceNotFoundException;
import cl.martinez.bono_medico.model.Especialidad;
import cl.martinez.bono_medico.model.Persona;
import cl.martinez.bono_medico.model.Profesional;
import cl.martinez.bono_medico.repository.EspecialidadRepository;
import cl.martinez.bono_medico.repository.PersonaRepository;
import cl.martinez.bono_medico.repository.ProfesionalRepository;
import cl.martinez.bono_medico.service.ProfesionalService;

@Service
public class ProfesionalServiceImpl implements ProfesionalService{

    @Autowired
    private ProfesionalRepository profesionalRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Override
    public ProfesionalDTO crear(ProfesionalDTO dto) {
        Persona persona = personaRepository.findById(dto.getIdPersona())
            .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada"));

        Especialidad especialidad = especialidadRepository.findById(dto.getIdEspecialidad())
            .orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada"));

        Profesional profesional = new Profesional();
        profesional.setPersona(persona);
        profesional.setEspecialidad(especialidad);

        Profesional guardado = profesionalRepository.save(profesional);

        dto.setIdProfesional(guardado.getIdProfesional());
        return dto;
    }

    @Override
    public ProfesionalDTO obtenerPorId(Integer id) {
        Profesional profesional = profesionalRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Profesional no encontrado"));

        ProfesionalDTO dto = new ProfesionalDTO();
        dto.setIdProfesional(profesional.getIdProfesional());
        dto.setIdPersona(profesional.getPersona().getIdPersona());
        dto.setIdEspecialidad(profesional.getEspecialidad().getIdEspecialidad());
        return dto;
    }

public List<ProfesionalDTO> obtenerTodos() {
    List<Profesional> profesionales = profesionalRepository.findAll();

    return profesionales.stream().map(prof -> {
        ProfesionalDTO dto = new ProfesionalDTO();
        dto.setIdProfesional(prof.getIdProfesional());

        // Persona
        Persona persona = prof.getPersona();
        if (persona != null) {
            dto.setIdPersona(persona.getIdPersona()); // <- ASIGNAR EL ID
            dto.setRut(persona.getRut());              // <- NUEVO CAMPO
            dto.setCorreo(persona.getCorreo());        // <- NUEVO CAMPO

            String nombreCompleto = persona.getNombre() + " " +
                                    persona.getApellidoPaterno() + " " +
                                    persona.getApellidoMaterno();
            dto.setNombreCompleto(nombreCompleto);
        } else {
            dto.setNombreCompleto("No asignado");
        }

        // Especialidad
        Especialidad esp = prof.getEspecialidad();
        if (esp != null) {
            dto.setIdEspecialidad(esp.getIdEspecialidad()); // <- ASIGNAR EL ID
            dto.setNombreEspecialidad(esp.getNombre());
        } else {
            dto.setNombreEspecialidad("No asignada");
        }

        return dto;
    }).collect(Collectors.toList());
}

    @Override
    public ProfesionalDTO actualizar(Integer id, ProfesionalDTO dto) {
        Profesional profesional = profesionalRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Profesional no encontrado"));

        Persona persona = personaRepository.findById(dto.getIdPersona())
            .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada"));

        Especialidad especialidad = especialidadRepository.findById(dto.getIdEspecialidad())
            .orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada"));

        profesional.setPersona(persona);
        profesional.setEspecialidad(especialidad);

        Profesional actualizado = profesionalRepository.save(profesional);
        dto.setIdProfesional(actualizado.getIdProfesional());
        return dto;
    }

    @Override
    public void eliminar(Integer id) {
        Profesional profesional = profesionalRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Profesional no encontrado"));
        profesionalRepository.delete(profesional);
    }

}
