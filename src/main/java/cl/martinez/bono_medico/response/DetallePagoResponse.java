package cl.martinez.bono_medico.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetallePagoResponse {
    private String mensaje;
    private Boolean exito;

    public DetallePagoResponse(String mensaje, Boolean exito){
        this.mensaje = mensaje;
        this.exito = exito;
    }

}
