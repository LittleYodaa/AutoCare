package pl.patrykkawula.autocare.email.emailPreparation;

import pl.patrykkawula.autocare.email.Email;

import java.util.List;

interface PrepareEmail {
    int ONE_DAY_TO_DATE = 1;
    int SEVEN_DAYS_TO_DATE = 7;
    List<Email> createListOfEmail();
}
