package pl.patrykkawula.autocare.email;

import org.springframework.stereotype.Service;
import pl.patrykkawula.autocare.car.CarRepository;

import java.time.LocalDate;
import java.util.List;
@Service
public class PrepareTechnicalInspectionEmail implements PrepareEmail{
    private final CarRepository carRepository;

    public PrepareTechnicalInspectionEmail(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Email> createListOfEmail() {
        return carRepository.getAllByTechnicalInspectionEndDate(LocalDate.now().plusDays(SEVEN_DAYS_TO_DATE)).stream()
                .map(IncomingTechnicalServiceEmailView::getUser)
                .map(IncomingTechnicalServiceEmailView.UserEmailView::getEmail)
                .map(this::createEmail)
                .toList();
    }

    private Email createEmail(String recipientEmail) {
        return Email.builder()
                .subject("Badanie techniczne")
                .text(String.format("Twoje badanie techniczne straci ważność za %s dni.", SEVEN_DAYS_TO_DATE))
                .emailRecipient(recipientEmail)
                .status(Email.Status.UNSENT)
                .build();
    }
}



