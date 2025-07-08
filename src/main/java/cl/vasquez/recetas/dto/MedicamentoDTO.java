package cl.vasquez.recetas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MedicamentoDTO {
    private Integer idCatalogoMedicamento; 
    private String nombreCatalogoMedicamento; 
    private String dosis; 
    private String frecuencia; 
    private String observaciones; 

    

}
