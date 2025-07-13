package cl.martinez.bono_medico.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.martinez.bono_medico.dto.PrevisionDTO;
import cl.martinez.bono_medico.exception.ResourceNotFoundException;
import cl.martinez.bono_medico.model.Prevision;
import cl.martinez.bono_medico.repository.PrevisionRepository;
import cl.martinez.bono_medico.service.PrevisionService;

@Service
public class PrevisionServiceImpl implements PrevisionService {

    @Autowired
    private PrevisionRepository previsionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PrevisionDTO crear(PrevisionDTO dto) {
        Prevision prevision = modelMapper.map(dto, Prevision.class);
        return modelMapper.map(previsionRepository.save(prevision), PrevisionDTO.class);
    }

    @Override
    public PrevisionDTO obtenerPorId(Integer id) {
        Prevision prevision = previsionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Prevision no encontrada con ID: " +id));
        return modelMapper.map(prevision, PrevisionDTO.class);
    }

    @Override
    public List<PrevisionDTO> obtenerTodos() {
        return previsionRepository.findAll().stream()
            .map(prevision -> modelMapper.map(prevision, PrevisionDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public PrevisionDTO actualizar(Integer id, PrevisionDTO dto) {
        Prevision prevision = previsionRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Prevision no encontrada por ID: "+ id));

        prevision.setNombre(dto.getNombre());
        return modelMapper.map(previsionRepository.save(prevision), PrevisionDTO.class);

    }

    @Override
    public void eliminar(Integer id) {
        Prevision prevision = previsionRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Prevision no encontrada con ID: "+ id));
        previsionRepository.delete(prevision);
    }

    @Override
    public List<PrevisionDTO> crearMultiples(List<PrevisionDTO> previsiones) {
        return previsiones.stream()
            .map(this::crear)
            .collect(Collectors.toList());
    }

}
