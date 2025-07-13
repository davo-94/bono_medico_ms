package cl.vasquez.recetas.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor
public class RecetaDTO {
    private Integer idReceta;
    private LocalDate fechaEmision;
    private String estado;
    private Integer idPaciente;
    private Integer idMedico;
    private List<MedicamentoDTO> medicamentos;
    private List<DiagnosticoDTO> diagnosticos;
    private String nombrePaciente;
    private String nombreMedico;

    // Constructor sin listas ni nombres (por si lo usas en POST)
    public RecetaDTO(Integer idReceta, LocalDate fechaEmision, String estado, Integer idPaciente, Integer idMedico) {
        this.idReceta = idReceta;
        this.fechaEmision = fechaEmision;
        this.estado = estado;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
    }

    public RecetaDTO(
    Integer idReceta,
    LocalDate fechaEmision,
    String estado,
    Integer idPaciente,
    Integer idMedico,
    List<MedicamentoDTO> medicamentos,
    List<DiagnosticoDTO> diagnosticos
) {
    this.idReceta = idReceta;
    this.fechaEmision = fechaEmision;
    this.estado = estado;
    this.idPaciente = idPaciente;
    this.idMedico = idMedico;
    this.medicamentos = medicamentos;
    this.diagnosticos = diagnosticos;
}

}
