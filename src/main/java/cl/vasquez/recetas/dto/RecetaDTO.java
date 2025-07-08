package cl.vasquez.recetas.dto;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor //Constructor vacío -> Importante para que Spring pueda instanciar el objeto cuando recibe un JSON.
@AllArgsConstructor //Constructor con todos los campos

public class RecetaDTO {
    private Integer idReceta; //Clave primaria
    private LocalDate fechaEmision; 
    private String estado; 
    private Integer idPaciente; //Solo ID, no el objeto completo
    private Integer idMedico; 
    private List<MedicamentoDTO> medicamentos; //Lista de medicamentos
    private List<DiagnosticoDTO> diagnosticos; //Lista de diagnósticos

    public RecetaDTO (Integer idReceta, LocalDate fechaEmision, String estado, Integer idPaciente, Integer idMedico) {
        this.idReceta = idReceta; 
        this.fechaEmision = fechaEmision; 
        this.estado = estado; 
        this.idPaciente = idPaciente; 
        this.idMedico = idMedico;
    }
}
