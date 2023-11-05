package pl.patrykkawula.autocare.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("patrykkawulaapp@gmail.com");
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());
        javaMailSender.send(message);
    }
}
