package cl.vasquez.recetas.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ErrorResponse {
    private String mensaje; 
    private int codigoError; 
    private String detalles; 
}
