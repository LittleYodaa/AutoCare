package pl.patrykkawula.autocare.email;

import java.util.List;

public interface PrepareEmail {
    int SEVEN_DAYS_TO_DATE = 7;
    List<Email> createListOfEmail();
}
