package pl.patrykkawula.autocare.email;

import jakarta.persistence.*;
import lombok.*;
import pl.patrykkawula.autocare.user.User;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailRecipient;
    private String text;
    private String subject;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate dateOfSend;
    @ManyToOne
    User user;

    public enum Status {
        SENT,
        UNSENT

    }
}
