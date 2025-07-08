package cl.vasquez.recetas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //Indica que esta clase manejará excepciones globalmente en toda la aplicación.
//Centraliza todo el manejo de excepciones.
public class GlobalExceptionHandler {


    //Método para manejar la ResourceNotFoundException
    @ExceptionHandler (ResourceNotFoundException.class)//Este método maneja específicamente esta excepción.
    //Cuando esta excepción es lanzada, Spring llama a este método y devolverá un ResponseEntity con un mensaje de error personalizado.
    public ResponseEntity<ErrorResponse> handleRosourceNotFoundException(ResourceNotFoundException ex) {
        //Crear la respuesta de error personalizada
        ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(), //El mensaje de la excepción
            HttpStatus.NOT_FOUND.value(), //Código de estado HTTP 404
            "Revisa los parámetros de la solicitud o verifica la existencia del recurso"
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);//Devolver la respuesta con el código 404
    }

    //Método para manejar excepciones generales (por ejemplo, otros tipos de errores)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        //Este método maneja cualquier otra excepción no controlada. Si ocurre un error, devuelve un mensaje de error genérico.
        ErrorResponse errorResponse = new ErrorResponse(
            "Error inesperado",
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.getMessage()
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); //Código 500
        }
}
