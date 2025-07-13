package cl.martinez.bono_medico.service;

import java.util.List;

import cl.martinez.bono_medico.dto.BonoMedicoDTO;

public interface BonoMedicoService {

    BonoMedicoDTO crear(BonoMedicoDTO dto);
    BonoMedicoDTO obtenerPorId(Integer id);
    List<BonoMedicoDTO> obtenerTodos();
    BonoMedicoDTO actualizar(Integer id, BonoMedicoDTO dto);
    void eliminar(Integer id);

}
