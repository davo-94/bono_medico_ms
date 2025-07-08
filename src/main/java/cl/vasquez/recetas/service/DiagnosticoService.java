package cl.vasquez.recetas.service;

import java.util.List;

import cl.vasquez.recetas.dto.DiagnosticoDTO;

public interface  DiagnosticoService {
    DiagnosticoDTO crearDiagnostico (DiagnosticoDTO diagnosticoDTO, Integer idReceta);

    DiagnosticoDTO actualizarDiagnostico(Integer id, DiagnosticoDTO diagnosticoDTO);

    void eliminarDiagnostico(Integer id);

    List<DiagnosticoDTO> obtenerTodosDiagnosticos();

    DiagnosticoDTO obtenerDiagnosticoPorId(Integer id);
}
