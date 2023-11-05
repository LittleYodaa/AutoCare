package pl.patrykkawula.autocare;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.patrykkawula.autocare.car.CarRepository;
import pl.patrykkawula.autocare.car.CarService;
import pl.patrykkawula.autocare.car.dtos.CarDto;
import pl.patrykkawula.autocare.user.UserService;
import pl.patrykkawula.autocare.email.Email;
import pl.patrykkawula.autocare.email.EmailService;
import pl.patrykkawula.autocare.email.EmailTemplate;

import java.time.LocalDate;

@Slf4j
@Service
public class ScheduledTasks {
    private static final int ONE_DAY_TO_DATE = 1;
    private static final int SEVEN_DAYS_TO_DATE = 7;
    private final EmailService emailService;
    private final EmailTemplate emailTemplate;
    private final CarRepository carRepository;
    private final CarService carService;
    private final UserService userService;

    public ScheduledTasks(EmailService emailService, EmailTemplate emailTemplate, CarRepository carRepository, CarService carService, UserService userService) {
        this.emailService = emailService;
        this.emailTemplate = emailTemplate;
        this.carRepository = carRepository;
        this.carService = carService;
        this.userService = userService;
    }

    @Transactional
    @Scheduled(cron = "0 10 10 * * *")
    public void addPlannedMileage() {
        carRepository.findAll().forEach(c -> c.setMileage(c.getMileage() + (c.getPlannedAnnualMileage() / 365)));
    }

    @Scheduled(cron = "* 10 10 * * *")
    public void checkTechnicalInspectionDate() {
        Long carId = carRepository.findIncomingTechnicaServiceDate(LocalDate.now().plusDays(SEVEN_DAYS_TO_DATE));
        if (carId != null) {
            CarDto carDto = carService.findById(carId);
            String emailAddress = userService.findById(carDto.userId()).email();
            Email email = emailTemplate.technicalInspectionEmail(emailAddress);
            emailService.sendEmail(email);
            log.info("Wysłano wiadomość email");
        }
    }
}
