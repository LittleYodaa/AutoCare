package pl.patrykkawula.autocare.exception;

public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException(Long id) {
        super("Car with id %d not found".formatted(id));
    }
}
