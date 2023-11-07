package pl.patrykkawula.autocare.email.dtos;

import lombok.Builder;

@Builder
public record EmailSendDto(
        String emailRecipient,
        String subject,
        String text
) {
}
