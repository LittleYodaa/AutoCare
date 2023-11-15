package pl.patrykkawula.autocare.email.emailPreparation;

import org.springframework.stereotype.Service;
import pl.patrykkawula.autocare.car.CarRepository;
import pl.patrykkawula.autocare.email.Email;
import pl.patrykkawula.autocare.email.IncomingEmailView;
import pl.patrykkawula.autocare.user.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
class PrepareInsuranceEmail implements PrepareEmail {
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public PrepareInsuranceEmail(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Email> createListOfEmail() {
        return carRepository.getAllByInsuranceEndDate(LocalDate.now().plusDays(SEVEN_DAYS_TO_DATE)).stream()
                .map(IncomingEmailView::getUser)
                .map(IncomingEmailView.UserEmailView::getEmail)
                .map(this::createEmail)
                .toList();
    }

    private Email createEmail(String recipientEmail) {
        return Email.builder()
                .subject("Ubezpieczenie")
                .text(String.format("Twoje ubezpieczenie straci ważność za %s dni.", SEVEN_DAYS_TO_DATE))
                .emailRecipient(recipientEmail)
                .status(Email.Status.UNSENT)
                .user(userRepository.findByEmail(recipientEmail))
                .build();
    }
}
