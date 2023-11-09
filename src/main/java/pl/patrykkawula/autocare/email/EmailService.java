package pl.patrykkawula.autocare.email;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.patrykkawula.autocare.email.dtos.EmailDto;

import java.time.LocalDate;
import java.util.List;

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
        emailRepository.findAllByStatus(Email.Status.UNSENT).forEach(email -> {
            email.setStatus(Email.Status.SENT);
            email.setDateOfSend(LocalDate.now());
        });
    }

    List<EmailDto> findAllWithUnsentStatus() {
        return emailRepository.findAllByStatus(Email.Status.UNSENT)
                .stream()
                .map(emailDtoMapper::mapToEmailDto)
                .toList();
    }

    List<EmailDto> findAll() {
        return emailRepository.findAll()
                .stream()
                .map(emailDtoMapper::mapToEmailDto)
                .toList();
    }
}
