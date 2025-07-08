package cl.vasquez.recetas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RECETA_MEDICAMENTO")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="medicamento_seq")
    @SequenceGenerator(name = "medicamento_seq", sequenceName= "medicamento_seq", allocationSize=1)
    @Column(name = "IDMEDICAMENTO")
    private Integer idMedicamento; 

    @Column(name = "DOSIS")
    private String dosis; 

    @Column(name = "FRECUENCIA")
    private String frecuencia; 

    @Column(name = "OBSERVACIONES")
    private String observaciones; 

    @ManyToOne 
    @JoinColumn (name= "ID_RECETA", nullable = false)
    private Receta receta;

    @ManyToOne 
    @JoinColumn (name = "ID_CATALOGO_MEDICAMENTO", nullable = false)
    private CatalogoMedicamento catalogoMedicamento; 
    
}
