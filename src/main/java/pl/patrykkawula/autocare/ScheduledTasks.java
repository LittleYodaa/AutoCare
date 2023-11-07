package pl.patrykkawula.autocare;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.patrykkawula.autocare.car.CarRepository;
import pl.patrykkawula.autocare.email.*;
import pl.patrykkawula.autocare.email.dtos.EmailDto;

import java.util.List;

@Slf4j
@Service
public class ScheduledTasks {
    private final EmailSendingService emailSendingService;
    private final CarRepository carRepository;
    private final PrepareEmail prepareEmail;
    private final EmailService emailService;

    public ScheduledTasks(EmailSendingService emailSendingService, CarRepository carRepository, PrepareEmail prepareEmail, EmailService emailService) {
        this.emailSendingService = emailSendingService;
        this.carRepository = carRepository;
        this.prepareEmail = prepareEmail;
        this.emailService = emailService;
    }

    @Transactional
    @Scheduled(cron = "0 10 10 * * *")
    public void addPlannedMileage() {
        carRepository.findAll().forEach(c -> c.setMileage(c.getMileage() + (c.getPlannedAnnualMileage() / 365)));
    }

//    @Scheduled(cron = "0 10 10 * * *")
    @Scheduled(cron = "20 * * * * *")
    @Transactional
    public void saveEmailToSend() {
        List<Email> listOfEmailToSave = prepareEmail.createListOfEmail();
        log.info("Prepare email to send");
        emailService.saveAll(listOfEmailToSave);
        log.info("Save email to send");
    }

//    @Scheduled(cron = "0 10 20 * * *")
    @Scheduled(cron = "30 * * * * *")
    @Transactional
    public void sendEmail() {
        emailSendingService.sendEmail();
        log.info("Send email");
    }
}
