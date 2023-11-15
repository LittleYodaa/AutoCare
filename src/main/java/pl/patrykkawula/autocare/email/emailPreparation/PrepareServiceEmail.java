package pl.patrykkawula.autocare.email.emailPreparation;

import pl.patrykkawula.autocare.car.CarRepository;
import pl.patrykkawula.autocare.email.Email;
import pl.patrykkawula.autocare.email.IncomingEmailView;
import pl.patrykkawula.autocare.user.UserRepository;

import java.time.LocalDate;
import java.util.List;

class PrepareServiceEmail implements PrepareEmail{
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    PrepareServiceEmail(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Email> createListOfEmail() {
        return carRepository.getAllByNextCarServiceDate(LocalDate.now().plusDays(SEVEN_DAYS_TO_DATE)).stream()
                .map(IncomingEmailView::getUser)
                .map(IncomingEmailView.UserEmailView::getEmail)
                .map(this::createEmail)
                .toList();
    }

    Email createEmail(String recipientEmail) {
        return Email.builder()
                .subject("Serwis")
                .text(String.format("Twoje auto wymaga serwisu za %s dni.", SEVEN_DAYS_TO_DATE))
                .emailRecipient(recipientEmail)
                .status(Email.Status.UNSENT)
                .user(userRepository.findByEmail(recipientEmail))
                .build();

    }
}
