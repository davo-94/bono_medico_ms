package cl.vasquez.recetas.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table (name= "RECETA_DIAGNOSTICO")
public class Diagnostico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diagnostico_seq")
    @SequenceGenerator(name = "diagnostico_seq", sequenceName = "diagnostico_seq", allocationSize=1)
    @Column (name = "IDDIAGNOSTICO")
    private Integer idDiagnostico; 

    @Column (name = "CODIGO")
    private String codigo; 

    @Column (name = "DESCRIPCION")
    private String descripcion; 

    @Column(name = "FECHADIAGNOSTICO")
    private LocalDate fechaDiagnostico;
    
    @ManyToOne 
    @JoinColumn (name = "ID_RECETA", nullable = false)
    private Receta receta; 

    //Constructor sin id
    public Diagnostico(String codigo, String descripcion, LocalDate fechaDiagnostico) {
        this.codigo = codigo; 
        this.descripcion = descripcion; 
        this.fechaDiagnostico = fechaDiagnostico; 
    }

   
}
