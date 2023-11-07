package pl.patrykkawula.autocare.email;

import org.springframework.stereotype.Service;
import pl.patrykkawula.autocare.email.dtos.EmailDto;
import pl.patrykkawula.autocare.email.dtos.EmailSendDto;
import pl.patrykkawula.autocare.user.User;
import pl.patrykkawula.autocare.user.UserRepository;

@Service
public class EmailDtoMapper {
    private final UserRepository userRepository;

    public EmailDtoMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    EmailSendDto mapToEmailSendDto(Email email) {
        return EmailSendDto.builder()
                .emailRecipient(email.getEmailRecipient())
                .text(email.getText())
                .subject(email.getSubject())
                .build();
    }

    EmailSendDto mapEmailDtoToEmailToSendDto(EmailDto emailDto) {
        return EmailSendDto.builder()
                .text(emailDto.text())
                .emailRecipient(emailDto.emailRecipient())
                .subject(emailDto.subject())
                .build();
    }

    EmailDto mapToEmailDto(Email email) {
        return EmailDto.builder()
                .id(email.getId())
                .emailRecipient(email.getEmailRecipient())
                .text(email.getText())
                .subject(email.getSubject())
                .status(email.getStatus())
                .dateOfSend(email.getDateOfSend())
                .userId(email.getUser().getId())
                .build();
    }

    Email map(EmailDto emailDto) {
        User user = userRepository.findById(emailDto.userId()).orElseThrow();
        return Email.builder()
                .id(emailDto.id())
                .emailRecipient(emailDto.emailRecipient())
                .text(emailDto.text())
                .subject(emailDto.subject())
                .status(emailDto.status())
                .dateOfSend(emailDto.dateOfSend())
                .user(user)
                .build();
    }

}
