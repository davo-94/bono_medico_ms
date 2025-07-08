package cl.vasquez.recetas.dto;

import lombok.AllArgsConstructor; 
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CatalogoMedicamentoDTO {
    private Integer idCatalogoMedicamento; 
    private String nombre; 
    private String principioActivo; 
    private String presentacion; 

}
