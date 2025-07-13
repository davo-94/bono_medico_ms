package cl.martinez.bono_medico.service;

import java.util.List;

import cl.martinez.bono_medico.dto.ProfesionalDTO;

public interface ProfesionalService {

    ProfesionalDTO crear(ProfesionalDTO dto);
    ProfesionalDTO obtenerPorId(Integer id);
    List<ProfesionalDTO> obtenerTodos();
    ProfesionalDTO actualizar(Integer id, ProfesionalDTO dto);
    void eliminar(Integer id);
}
