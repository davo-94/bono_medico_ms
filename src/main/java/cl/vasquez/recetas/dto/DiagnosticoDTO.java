package cl.vasquez.recetas.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class DiagnosticoDTO {
    private String codigo; 
    private String descripcion; 
    private LocalDate fechaDiagnostico; 
    private Integer idReceta; 

    public DiagnosticoDTO(String codigo, String descripcion, LocalDate fechaDiagnostico) {
    this.codigo = codigo;
    this.descripcion = descripcion;
    this.fechaDiagnostico = fechaDiagnostico;
}
}
