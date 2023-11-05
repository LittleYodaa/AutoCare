package pl.patrykkawula.autocare.email;

import org.springframework.stereotype.Service;

@Service
public class EmailTemplate {

    private static final int ONE_DAY_TO_DATE = 1;
    private static final int SEVEN_DAYS_TO_DATE = 7;
    private static final String TECHNICAL_INSPECTION = "badanie techniczne";
    private static final String INSURANCE = "ubezpieczenie";
    private static final String PAYMENT_RATE_DATE = "payment rate";
    private static final String SERVICE = "service";

    //todo
    //static final do enuma
    //zapisywanie maili w bazie


    public Email technicalInspectionEmail (String emailAddress) {
        Email email = new Email();
        email.setSubject(String.format("Twoje %s się kończy!", TECHNICAL_INSPECTION));
        email.setText(String.format("Twoje %s straci ważność za %s dni.", TECHNICAL_INSPECTION, SEVEN_DAYS_TO_DATE));
        email.setTo(emailAddress);
        return email;
    }

    public Email insuranceEmail (String emailAddress) {
        Email email = new Email();
        email.setSubject(String.format("Twoje %s się kończy!", INSURANCE));
        email.setText(String.format("Twoje %s straci ważność za %s dni.", INSURANCE, SEVEN_DAYS_TO_DATE));
        email.setTo(emailAddress);
        return email;
    }
}
