package pl.patrykkawula.autocare.email;

import java.util.List;

public interface PrepareEmail {
    List<Email> createListOfEmail();
    List<Long> findCarWithIncomingEvent();
}
