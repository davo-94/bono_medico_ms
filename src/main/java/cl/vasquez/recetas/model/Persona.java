package cl.vasquez.recetas.model;

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
@Table(name = "persona")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persona_seq_gen")
    @SequenceGenerator(name = "persona_seq_gen", sequenceName = "persona_seq_gen", allocationSize = 1)
    @Column(name = "ID_PERSONA", nullable = false)
    private Integer idPersona;

    @Column(nullable = false, unique = true)
    private Integer rut;

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    @Column(unique = true)
    private String correo;

    private String fechaNacimiento;
    private String direccion;
    private Integer telefono;

    public Persona(Integer idPersona) {
        this.idPersona = idPersona;
    }

}
