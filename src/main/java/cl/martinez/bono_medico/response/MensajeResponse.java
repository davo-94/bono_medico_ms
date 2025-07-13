package cl.martinez.bono_medico.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MensajeResponse {
    private String mensaje;
    private boolean succes;

}
