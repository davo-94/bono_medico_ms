package cl.martinez.bono_medico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BonoMedicoDTO {

    private Integer idBono;
    private String fechaEmision;
    private String fechaVencimiento;
    private String estado;
    private Integer precioTotal;

    private Integer idPersona;
    private Integer idProfesional;
}
