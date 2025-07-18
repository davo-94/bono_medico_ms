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
@Table(name = "PREVISION")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Prevision {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prevision_seq_gen")
    @SequenceGenerator(name = "prevision_seq_gen", sequenceName = "prevision_seq_gen", allocationSize = 1)
    @Column(name = "ID_PREVISION", nullable = false)
    private Integer idPrevision;

    @Column(name = "NOMBRE")
    private String nombre;
}
