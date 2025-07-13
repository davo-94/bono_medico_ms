package cl.martinez.bono_medico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfesionalDTO {

    private Integer idProfesional;
    private Integer idPersona;
    private Integer idEspecialidad;
    private String nombreCompleto;
    private String nombreEspecialidad;
    private Integer rut;
    private String correo;
}
