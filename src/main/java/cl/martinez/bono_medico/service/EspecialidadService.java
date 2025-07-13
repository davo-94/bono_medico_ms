package cl.martinez.bono_medico.service;

import java.util.List;

import cl.martinez.bono_medico.dto.EspecialidadDTO;

public interface EspecialidadService {

    EspecialidadDTO crear(EspecialidadDTO dto);
    EspecialidadDTO obtenerPorId(Integer id);
    List<EspecialidadDTO> obtenerTodos();
    EspecialidadDTO actualizar(Integer id, EspecialidadDTO dto);
    void eliminar(Integer id);
    List<EspecialidadDTO> crearMultiples(List<EspecialidadDTO> especialidades);
}
