package cl.martinez.bono_medico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO {

    private Integer idPersona;
    private Integer rut;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String fechaNacimiento;
    private String direccion;
    private Integer telefono;
    private Integer idPrevision;
    private String nombrePrevision;

}
