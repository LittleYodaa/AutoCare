package pl.patrykkawula.autocare.email;

public interface IncomingTechnicalServiceEmailView {
    UserEmailView getUser();

    interface UserEmailView {
        String getEmail();
    }
}
