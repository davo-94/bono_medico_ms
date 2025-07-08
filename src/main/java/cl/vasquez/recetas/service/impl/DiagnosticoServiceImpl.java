package cl.vasquez.recetas.service.impl;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.vasquez.recetas.dto.DiagnosticoDTO;
import cl.vasquez.recetas.model.Diagnostico;
import cl.vasquez.recetas.model.Receta;
import cl.vasquez.recetas.repository.DiagnosticoRepository;
import cl.vasquez.recetas.repository.RecetaRepository;
import cl.vasquez.recetas.service.DiagnosticoService;

@Service
public class DiagnosticoServiceImpl implements DiagnosticoService {

    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    @Autowired
    private RecetaRepository recetaRepository;
    
    @Override
    public DiagnosticoDTO crearDiagnostico (DiagnosticoDTO dto, Integer idReceta) {
    Receta receta = recetaRepository.findById(idReceta)
        .orElseThrow(() -> new RuntimeException ("Receta no encontrada."));

    Diagnostico diagnostico = new Diagnostico();
    diagnostico.setCodigo(dto.getCodigo());
    diagnostico.setDescripcion(dto.getDescripcion());
    diagnostico.setFechaDiagnostico(dto.getFechaDiagnostico());
    diagnostico.setReceta(receta);

    Diagnostico guardado = diagnosticoRepository.save(diagnostico);

    return new DiagnosticoDTO(
        guardado.getCodigo(),
        guardado.getDescripcion(),
        guardado.getFechaDiagnostico(),
        receta.getIdReceta()
    );
}


    public DiagnosticoDTO actualizarDiagnostico(Integer id, DiagnosticoDTO diagnosticoDTO) {
    Diagnostico diagnostico = diagnosticoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Diagnóstico no encontrado"));

    diagnostico.setCodigo(diagnosticoDTO.getCodigo());
    diagnostico.setDescripcion(diagnosticoDTO.getDescripcion());
    diagnostico.setFechaDiagnostico(diagnosticoDTO.getFechaDiagnostico());
    // Si manejas idReceta en el DTO:
    Receta receta = recetaRepository.findById(diagnosticoDTO.getIdReceta())
            .orElseThrow(() -> new RuntimeException("Receta no encontrada"));
    diagnostico.setReceta(receta);

    Diagnostico actualizado = diagnosticoRepository.save(diagnostico);

    return new DiagnosticoDTO(
            actualizado.getCodigo(),
            actualizado.getDescripcion(),
            actualizado.getFechaDiagnostico(),
            actualizado.getReceta().getIdReceta()
    );
}

@Override
public void eliminarDiagnostico(Integer id) {
    if (!diagnosticoRepository.existsById(id)) {
        throw new RuntimeException("Diagnóstico no encontrado");
    }
    diagnosticoRepository.deleteById(id);
}

@Override
public List<DiagnosticoDTO> obtenerTodosDiagnosticos() {
    List<Diagnostico> diagnosticos = diagnosticoRepository.findAll();
    return diagnosticos.stream()
            .map(d -> new DiagnosticoDTO(
                    d.getCodigo(),
                    d.getDescripcion(),
                    d.getFechaDiagnostico(),
                    d.getReceta().getIdReceta() // <-- Añadir para consistencia
            ))
            .collect(Collectors.toList());
}

@Override
public DiagnosticoDTO obtenerDiagnosticoPorId(Integer id) {
    Diagnostico diagnostico = diagnosticoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Diagnóstico no encontrado"));
    return new DiagnosticoDTO(
            diagnostico.getCodigo(),
            diagnostico.getDescripcion(),
            diagnostico.getFechaDiagnostico(),
            diagnostico.getReceta().getIdReceta() // <-- Añadir para consistencia
    );
}

}
