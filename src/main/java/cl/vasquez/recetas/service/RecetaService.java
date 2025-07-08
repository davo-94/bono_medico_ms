package cl.vasquez.recetas.service;
import java.util.List;

import cl.vasquez.recetas.dto.RecetaDTO;

//Contrato que define qué operaciones ofrece la lógica de negocio. 
//No se implementa la lógica, solo se define qué métodos tendrá.
public interface RecetaService {
    RecetaDTO crearReceta(RecetaDTO recetaDTO); 
    RecetaDTO obtenerPorId(Integer id); 
    List<RecetaDTO> obtenerTodas();
    RecetaDTO actualizarEstado(Integer id, String nuevoEstado);
    void eliminarReceta(Integer id); 
}
