package cl.vasquez.recetas.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.vasquez.recetas.dto.CatalogoMedicamentoDTO;
import cl.vasquez.recetas.model.CatalogoMedicamento;
import cl.vasquez.recetas.repository.CatalogoMedicamentoRepository;
import cl.vasquez.recetas.service.CatalogoMedicamentoService;

@Service
public class CatalogoMedicamentoServiceImpl implements CatalogoMedicamentoService {

    @Autowired 
    private CatalogoMedicamentoRepository catalogoMedicamentoRepository; 

    @Override
    public CatalogoMedicamentoDTO crearCatalogoMedicamento (CatalogoMedicamentoDTO dto){

        CatalogoMedicamento catalogo = new CatalogoMedicamento(); 
        catalogo.setNombre(dto.getNombre());
        catalogo.setPrincipioActivo(dto.getPrincipioActivo()); 
        catalogo.setPresentacion(dto.getPresentacion()); 
        catalogo = catalogoMedicamentoRepository.save(catalogo); 
        
        return new CatalogoMedicamentoDTO(
            catalogo.getIdCatalogoMedicamento(), 
            catalogo.getNombre(), 
            catalogo.getPrincipioActivo(), 
            catalogo.getPresentacion()
        );
    }

    @Override
    public List<CatalogoMedicamentoDTO> obtenerTodos() {
        List<CatalogoMedicamento> catalogos = catalogoMedicamentoRepository.findAll(); 
        return catalogos.stream()
        .map (c -> new CatalogoMedicamentoDTO(
            c.getIdCatalogoMedicamento(), 
            c.getNombre(),
            c.getPrincipioActivo(), 
            c.getPresentacion()))
            .collect (Collectors.toList()); 
        
    }
}
