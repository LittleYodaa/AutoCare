package pl.patrykkawula.autocare.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.patrykkawula.autocare.email.dtos.EmailDto;
import pl.patrykkawula.autocare.email.dtos.EmailSendDto;

import java.util.List;

@Service
public class EmailSendingService {
    private final JavaMailSender javaMailSender;
    private final EmailService emailService;
    private final EmailDtoMapper emailDtoMapper;

    public EmailSendingService(JavaMailSender javaMailSender, EmailService emailService, EmailDtoMapper emailDtoMapper) {
        this.javaMailSender = javaMailSender;
        this.emailService = emailService;
        this.emailDtoMapper = emailDtoMapper;

    }

    public void sendEmail() {
        List<EmailDto> emailDtos = emailService.findAllWithUnsentStatus();
        List<EmailSendDto> emailSendDtos = emailDtos.stream()
                .map(emailDtoMapper::mapEmailDtoToEmailToSendDto)
                .toList();
        createMessage(emailSendDtos).forEach(javaMailSender::send);
        emailService.updateAllEmailsStatusAndDateOfSend();
    }

    private List<SimpleMailMessage> createMessage(List<EmailSendDto> emailSendDtos) {
        return emailSendDtos.stream()
                .map(email -> {
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setTo(email.emailRecipient());
                    message.setSubject(email.subject());
                    message.setText(email.text());
                    message.setFrom("patrykkawulaapp@gmail.com");
                    return message;
                })
                .toList();
    }
}
