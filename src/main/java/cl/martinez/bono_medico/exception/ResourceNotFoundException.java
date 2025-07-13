package cl.martinez.bono_medico.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }

}
