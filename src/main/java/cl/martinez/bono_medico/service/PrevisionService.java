package cl.martinez.bono_medico.service;

import java.util.List;

import cl.martinez.bono_medico.dto.PrevisionDTO;

public interface PrevisionService {
    PrevisionDTO crear(PrevisionDTO dto);
    PrevisionDTO obtenerPorId(Integer id);
    List<PrevisionDTO> obtenerTodos();
    PrevisionDTO actualizar(Integer id, PrevisionDTO dto);
    void eliminar(Integer id);
    List<PrevisionDTO> crearMultiples(List<PrevisionDTO> previsiones);

}
