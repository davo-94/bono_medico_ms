package cl.martinez.bono_medico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetallePagoBonoDTO {

    private Integer idPago;
    private String medioPago;
    private Integer idBono;
    private Integer montoPagado;
    private String fechaPago;
}
