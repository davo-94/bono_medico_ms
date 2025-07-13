package cl.martinez.bono_medico.mapper;

import org.springframework.stereotype.Component;

import cl.martinez.bono_medico.dto.BonoMedicoDTO;
import cl.martinez.bono_medico.model.BonoMedico;
import cl.martinez.bono_medico.model.Persona;
import cl.martinez.bono_medico.model.Profesional;

@Component
public class BonoMedicoMapper {

    public BonoMedico toEntity(BonoMedicoDTO dto){
        BonoMedico entity = new BonoMedico();
        entity.setIdBono(dto.getIdBono());
        entity.setEstado(dto.getEstado());

        entity.setPersona(new Persona());
        entity.getPersona().setIdPersona(dto.getIdPersona());

        entity.setProfesional(new Profesional());
        entity.getProfesional().setIdProfesional(dto.getIdProfesional());

        return entity;
    }

    public BonoMedicoDTO toDTO(BonoMedico entity){
        BonoMedicoDTO dto = new BonoMedicoDTO();
        dto.setIdBono(entity.getIdBono());
        dto.setEstado(entity.getEstado());
        dto.setFechaEmision(entity.getFechaEmision());
        dto.setFechaVencimiento(entity.getFechaVencimiento());
        dto.setPrecioTotal(entity.getPrecio_total());

        dto.setIdPersona(entity.getPersona().getIdPersona());
        dto.setIdProfesional(entity.getProfesional().getIdProfesional());

        return dto;
    }
}
