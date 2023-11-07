package pl.patrykkawula.autocare.email;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.patrykkawula.autocare.email.dtos.EmailDto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Service
public class EmailService {
    private final EmailRepository emailRepository;
    private final EmailDtoMapper emailDtoMapper;

    public EmailService(EmailRepository emailRepository, EmailDtoMapper emailDtoMapper) {
        this.emailRepository = emailRepository;
        this.emailDtoMapper = emailDtoMapper;
    }


    public void saveAll(List<Email> emails) {
        emailRepository.saveAll(emails);
    }

    @Transactional
    public void updateAllEmailsStatusAndDateOfSend() {
        emailRepository.findAllByStatusEqualsUnsent().forEach(email -> {
            email.setStatus(Email.Status.SENT);
            email.setDateOfSend(LocalDate.now());
        });
    }

    List<EmailDto> findAllWithUnsentStatus() {
        return emailRepository.findAllByStatusEqualsUnsent()
                .stream()
                .map(emailDtoMapper::mapToEmailDto)
                .toList();
    }
}
