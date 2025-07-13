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
@Table(name = "DETALLE_PAGO_BONO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetallePagoBono {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalle_pago_bono_seq_gen")
    @SequenceGenerator(name = "detalle_pago_bono_seq_gen", sequenceName = "detalle_pago_bono_seq", allocationSize = 1)
    @Column(name = "ID_PAGO")
    private Integer idPago;

    @Column(name = "MONTO_PAGADO", nullable = false)
    private Integer montoPagado;

    @Column(name = "MEDIO_PAGO", nullable = false)
    private String medioPago;

    @Column(name = "FECHA_PAGO", nullable = false)
    private String fechaPago;

    @ManyToOne
    @JoinColumn(name = "ID_BONO", nullable = false)
    private BonoMedico bono;
}
