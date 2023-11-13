package pl.patrykkawula.autocare.email;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrepareAllEmails {
    private final List<PrepareEmail> prepareEmails;

    public PrepareAllEmails(List<PrepareEmail> prepareEmails) {
        this.prepareEmails = prepareEmails;
    }

    public List<Email> prepareAllEmailsToSend() {
        List<Email> emailToSend = new ArrayList<>();
        prepareEmails.forEach(prepareEmail -> emailToSend.addAll(prepareEmail.createListOfEmail()));
        return emailToSend;
    }
}
