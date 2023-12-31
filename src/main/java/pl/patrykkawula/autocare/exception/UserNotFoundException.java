package pl.patrykkawula.autocare.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("User with id %d not found".formatted(id));
    }
}
