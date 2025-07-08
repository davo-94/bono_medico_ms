package cl.vasquez.recetas.service;

import java.util.List;

import cl.vasquez.recetas.dto.CatalogoMedicamentoDTO;

public interface  CatalogoMedicamentoService {
    
    CatalogoMedicamentoDTO crearCatalogoMedicamento (CatalogoMedicamentoDTO catalogoMedicamentoDTO);
    List<CatalogoMedicamentoDTO> obtenerTodos(); 
}
