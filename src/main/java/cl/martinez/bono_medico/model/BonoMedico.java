package cl.martinez.bono_medico.model;

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
@Table(name = "BONO_MEDICO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BonoMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bono_medico_seq_gen")
    @SequenceGenerator(name = "bono_medico_seq_gen", sequenceName = "bono_medico_seq_gen", allocationSize = 1)
    @Column(name = "ID_BONO", nullable = false)
    private Integer idBono;

    @Column(name = "FECHA_EMISION")
    private String fechaEmision;

    @Column(name = "FECHA_VENCIMIENTO")
    private String fechaVencimiento;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "PRECIO_TOTAL", nullable = false)
    private Integer precio_total;

    @ManyToOne
    @JoinColumn(name = "ID_PERSONA", nullable = false)
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "ID_PROFESIONAL", nullable = false)
    private Profesional profesional;

}
