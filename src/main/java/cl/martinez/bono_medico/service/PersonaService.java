package cl.martinez.bono_medico.service;

import java.util.List;

import cl.martinez.bono_medico.dto.PersonaDTO;

public interface PersonaService {
    PersonaDTO crear(PersonaDTO personaDTO);
    PersonaDTO obtenerPorId(Integer id);
    List<PersonaDTO> obtenerTodos();
    PersonaDTO actualizar(Integer id, PersonaDTO personaDTO);
    void eliminar(Integer id);
    PersonaDTO buscarPorRut(Integer rut);

}
