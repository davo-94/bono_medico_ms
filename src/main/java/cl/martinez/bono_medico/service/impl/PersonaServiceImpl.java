package cl.martinez.bono_medico.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.martinez.bono_medico.dto.PersonaDTO;
import cl.martinez.bono_medico.exception.ResourceNotFoundException;
import cl.martinez.bono_medico.model.Persona;
import cl.martinez.bono_medico.model.Prevision;
import cl.martinez.bono_medico.repository.PersonaRepository;
import cl.martinez.bono_medico.repository.PrevisionRepository;
import cl.martinez.bono_medico.service.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    private PersonaRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PrevisionRepository previsionRepository;

    /*@Override
    public PersonaDTO crear(PersonaDTO personaDTO) {
        Persona persona = modelMapper.map(personaDTO, Persona.class);
            // Obtener prevision desde la base por ID
    Optional<Prevision> previsionOpt = previsionRepository.findById(personaDTO.getIdPrevision());
    if (previsionOpt.isPresent()) {
        Prevision prevision = previsionOpt.get();
        persona.setPrevision(prevision);
        persona.setNombrePrevision(prevision.getNombre()); // <--- Aquí se llena automáticamente
    } else {
        throw new ResourceNotFoundException("Previsión no encontrada");
    }
        return modelMapper.map(repository.save(persona), PersonaDTO.class);
    }*/

    @Override
public PersonaDTO crear(PersonaDTO personaDTO) {
    // Mapear solo los campos básicos desde el DTO
    Persona persona = modelMapper.map(personaDTO, Persona.class);

    // Buscar la prevision
    Optional<Prevision> previsionOpt = previsionRepository.findById(personaDTO.getIdPrevision());
    if (previsionOpt.isEmpty()) {
        throw new ResourceNotFoundException("Previsión no encontrada");
    }

    Prevision prevision = previsionOpt.get();

    // Setear la relación
    persona.setPrevision(prevision);

    // Setear el campo adicional si existe en la tabla
    persona.setNombrePrevision(prevision.getNombre());

    // Guardar y mapear de vuelta al DTO
    Persona personaGuardada = repository.save(persona);
    PersonaDTO dto = modelMapper.map(personaGuardada, PersonaDTO.class);
    dto.setNombrePrevision(prevision.getNombre()); // para enviar al frontend

    return dto;
}

    @Override
    public PersonaDTO obtenerPorId(Integer id) {
        Persona persona = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada por ID: " + id));
        return modelMapper.map(persona, PersonaDTO.class);
    }

    @Override
    public List<PersonaDTO> obtenerTodos() {
        return repository.findAll().stream()
            .map(persona -> modelMapper.map(persona, PersonaDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public PersonaDTO actualizar(Integer id, PersonaDTO personaDTO) {
        Persona persona = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada por ID: "+ id));
        
        modelMapper.map(personaDTO, persona);
        return modelMapper.map(repository.save(persona), PersonaDTO.class);
    }

    @Override
    public void eliminar(Integer id) {
        Persona persona = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada por ID: "+ id));
        repository.delete(persona);
    }

    @Override
    public PersonaDTO buscarPorRut(Integer rut) {
    Persona persona = repository.findByRut(rut)
        .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada por RUT: " + rut));
    persona.setNombrePrevision(persona.getPrevision().getNombre());
    
    return modelMapper.map(persona, PersonaDTO.class);
    }

}
