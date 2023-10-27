package pl.patrykkawula.autocare.user.Exception;

public class UserNotExistException extends RuntimeException{
    public UserNotExistException(Long id){
        super("User with id %d do not exist".formatted(id));
    }
}
