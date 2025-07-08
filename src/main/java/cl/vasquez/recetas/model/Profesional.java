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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PROFESIONAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profesional {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFESIONAL_SEQ_GEN")
    @SequenceGenerator(name = "PROFESIONAL_SEQ_GEN", sequenceName = "PROFESIONAL_SEQ_GEN", allocationSize = 1)
    @Column(name = "ID_PROFESIONAL")
    private Integer idProfesional;

    @ManyToOne
    @JoinColumn(name = "ID_PERSONA")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "ID_ESPECIALIDAD")
    private Especialidad especialidad;

    public Profesional(Integer idProfesional){
        this.idProfesional = idProfesional;
    }

}
