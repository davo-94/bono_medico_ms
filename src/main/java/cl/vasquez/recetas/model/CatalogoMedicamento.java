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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CATALOGO_MEDICAMENTOS")
public class CatalogoMedicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catalogo_medicamento_seq")
    @SequenceGenerator(name = "catalogo_medicamento_seq", sequenceName = "catalogo_medicamento_seq", allocationSize = 1)
    @Column(name = "ID_CATALOGO_MEDICAMENTO")
    private Integer idCatalogoMedicamento; 

    @Column(name = "NOMBRE", nullable = false)
    private String nombre; 

    @Column(name = "PRINCIPIO_ACTIVO")
    private String principioActivo; 

    @Column(name = "PRESENTACION")
    private String presentacion; 
}
