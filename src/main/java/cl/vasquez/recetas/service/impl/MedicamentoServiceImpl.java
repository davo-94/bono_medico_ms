package cl.vasquez.recetas.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.vasquez.recetas.dto.MedicamentoDTO;
import cl.vasquez.recetas.exception.ResourceNotFoundException;
import cl.vasquez.recetas.model.CatalogoMedicamento;
import cl.vasquez.recetas.model.Medicamento;
import cl.vasquez.recetas.model.Receta;
import cl.vasquez.recetas.repository.CatalogoMedicamentoRepository;
import cl.vasquez.recetas.repository.MedicamentoRepository;
import cl.vasquez.recetas.repository.RecetaRepository;
import cl.vasquez.recetas.service.MedicamentoService;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    @Autowired
    private CatalogoMedicamentoRepository catalogoMedicamentoRepository; 

    @Autowired
    private MedicamentoRepository medicamentoRepository; 

    @Autowired
    private RecetaRepository recetaRepository; 
    
    @Override
    public MedicamentoDTO crearMedicamento(MedicamentoDTO medicamentoDTO, Integer idReceta) {
        // Validar que exista la receta
        Receta receta = recetaRepository.findById(idReceta) 
            .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada con id: " + idReceta));
        // Validar que exista el medicamento en el catálogo
            CatalogoMedicamento catalogoMedicamento = catalogoMedicamentoRepository.findById(medicamentoDTO.getIdCatalogoMedicamento())
            .orElseThrow(() -> new ResourceNotFoundException("Medicamento del catálogo no encontrado con id: "+ medicamentoDTO.getIdCatalogoMedicamento()));
        

            //Crear entidad Medicamento
        Medicamento medicamento = new Medicamento ();
            medicamento.setCatalogoMedicamento(catalogoMedicamento);
            medicamento.setDosis(medicamentoDTO.getDosis());
            medicamento.setFrecuencia(medicamentoDTO.getFrecuencia());
            medicamento.setObservaciones(medicamentoDTO.getObservaciones());
            medicamento.setReceta(receta);
        //Guardar en la bd
        medicamento = medicamentoRepository.save(medicamento);

        //Devolver DTO 
        MedicamentoDTO resultadoDTO = new MedicamentoDTO();
        resultadoDTO.setIdCatalogoMedicamento(catalogoMedicamento.getIdCatalogoMedicamento());
        resultadoDTO.setDosis(medicamento.getDosis());
        resultadoDTO.setFrecuencia(medicamento.getFrecuencia());
        resultadoDTO.setObservaciones(medicamento.getObservaciones());
        return resultadoDTO;
    }

    @Override
    public List<MedicamentoDTO> obtenerMedicamentosPorReceta(Integer idReceta) {
    Receta receta = recetaRepository.findById(idReceta)
            .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada con id: " + idReceta));

    return receta.getMedicamentos().stream()
            .map(med -> new MedicamentoDTO(
                    med.getCatalogoMedicamento().getIdCatalogoMedicamento(),
                    med.getCatalogoMedicamento().getNombre(),
                    med.getDosis(),
                    med.getFrecuencia(),
                    med.getObservaciones()
            ))
            .collect(Collectors.toList());
}

}
