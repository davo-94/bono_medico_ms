package cl.vasquez.recetas.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException (String mensaje) {
        super(mensaje); //Llama al constructor de RuntimeExcepcion con el mensaje de error
    }

}
