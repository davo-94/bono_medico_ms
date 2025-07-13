package cl.martinez.bono_medico.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ESPECIALIDAD")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "especialidad_seq_gen")
    @SequenceGenerator(name = "especialidad_seq_gen", sequenceName = "especialidad_seq_gen", allocationSize = 1)
    @Column(name = "ID_ESPECIALIDAD", nullable = false)
    private Integer idEspecialidad;

    @Column(name = "NOMBRE")
    private String nombre;
}
