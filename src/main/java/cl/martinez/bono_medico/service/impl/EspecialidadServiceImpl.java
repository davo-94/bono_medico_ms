package cl.martinez.bono_medico.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.martinez.bono_medico.dto.EspecialidadDTO;
import cl.martinez.bono_medico.exception.ResourceNotFoundException;
import cl.martinez.bono_medico.model.Especialidad;
import cl.martinez.bono_medico.repository.EspecialidadRepository;
import cl.martinez.bono_medico.service.EspecialidadService;

@Service
public class EspecialidadServiceImpl implements EspecialidadService{

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EspecialidadDTO crear(EspecialidadDTO dto) {
        Especialidad especialidad = modelMapper.map(dto, Especialidad.class);
        return modelMapper.map(especialidadRepository.save(especialidad), EspecialidadDTO.class);
    }

    @Override
    public EspecialidadDTO obtenerPorId(Integer id) {
        Especialidad especialidad = especialidadRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Especialidad no encontrada con ID: "+ id));
        return modelMapper.map(especialidad, EspecialidadDTO.class);
    }

    @Override
    public List<EspecialidadDTO> obtenerTodos() {
        return especialidadRepository.findAll().stream()
            .map(especialidad -> modelMapper.map(especialidad, EspecialidadDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public EspecialidadDTO actualizar(Integer id, EspecialidadDTO dto) {
        Especialidad especialidad = especialidadRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada con ID: "+ id));

        especialidad.setNombre(dto.getNombre());
        return modelMapper.map(especialidadRepository.save(especialidad), EspecialidadDTO.class);
    }

    @Override
    public void eliminar(Integer id) {
        Especialidad especialidad = especialidadRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada con ID: "+id));
        especialidadRepository.delete(especialidad);
    }

    @Override
    public List<EspecialidadDTO> crearMultiples(List<EspecialidadDTO> especialidades) {
        return especialidades.stream()
            .map(this::crear) //Usa el metodo crear individual
            .collect(Collectors.toList());
    }

}
