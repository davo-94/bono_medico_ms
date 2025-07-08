package cl.vasquez.recetas.service;

import java.util.List;

import cl.vasquez.recetas.dto.MedicamentoDTO;

public interface MedicamentoService {

    MedicamentoDTO crearMedicamento (MedicamentoDTO medicamentoDTO, Integer idReceta);
    
    List<MedicamentoDTO> obtenerMedicamentosPorReceta(Integer idReceta);

}
