package pl.patrykkawula.autocare.email;

public interface IncomingEmailView {
    UserEmailView getUser();

    interface UserEmailView {
        String getEmail();
    }
}
