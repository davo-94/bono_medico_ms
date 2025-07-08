package cl.vasquez.recetas.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity //Esta clase es una tabla
@Table (name = "RECETA_MEDICA") //La tabla de la BD se llama RECETA_MEDICA
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receta_seq") //Id generado automaticamente incrementado en 1
    @SequenceGenerator(name = "receta_seq", sequenceName = "RECETA_SEQ", allocationSize = 1)
    @Column(name = "IDRECETA")
    private Integer idReceta; 
    
    private LocalDate fechaEmision; 
    private String estado; //PENDIENTE, ENTREGADA

    @ManyToOne
    @JoinColumn(name = "ID_PACIENTE", referencedColumnName= "ID_PERSONA", nullable = false)
    private Persona paciente; // Relación con tabla PERSONA del proyecto original

    @ManyToOne
    @JoinColumn(name = "ID_MEDICO", referencedColumnName="ID_PROFESIONAL", nullable = false)
    private Profesional medico; // Relación con tabla PROFESIONAL

    // Relación con Medicamentos y Diagnósticos 
    
    

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medicamento> medicamentos = new ArrayList<>();
    
    
    @OneToMany (mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diagnostico> diagnosticos = new ArrayList<>();
    
    


}
