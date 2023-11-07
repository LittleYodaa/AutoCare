package pl.patrykkawula.autocare.email.dtos;

import lombok.Builder;
import pl.patrykkawula.autocare.email.Email;

import java.time.LocalDate;

@Builder
public record EmailDto (
        Long id,
        String emailRecipient,
        String text,
        String subject,
        Email.Status status,

        LocalDate dateOfSend,
        Long userId
) {
}
