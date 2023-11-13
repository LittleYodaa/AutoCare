package pl.patrykkawula.autocare;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.patrykkawula.autocare.car.CarRepository;
import pl.patrykkawula.autocare.email.*;

import java.util.List;

@Slf4j
@Service
public class ScheduledTasks {
    private final EmailSendingService emailSendingService;
    private final CarRepository carRepository;
    private final PrepareEmail prepareEmail;
    private final EmailService emailService;
    private final PrepareAllEmails prepareAllEmails;

    public ScheduledTasks(EmailSendingService emailSendingService, CarRepository carRepository, PrepareEmail prepareEmail, EmailService emailService, PrepareAllEmails prepareAllEmails) {
        this.emailSendingService = emailSendingService;
        this.carRepository = carRepository;
        this.prepareEmail = prepareEmail;
        this.emailService = emailService;
        this.prepareAllEmails = prepareAllEmails;
    }

    @Transactional
//    @Scheduled(cron = "0 10 10 * * *")
    public void addPlannedMileage() {
        carRepository.findAll().forEach(c -> c.setMileage(c.getMileage() + (c.getPlannedAnnualMileage() / 365)));
    }

//    @Scheduled(cron = "0 20 10 * * *")
    @Transactional
    public void saveEmailToSend() {
//        List<Email> listOfEmailToSave = prepareEmail.createListOfEmail();
        List<Email> listOfEmailToSave = prepareAllEmails.prepareAllEmailsToSend();
        emailService.saveAll(listOfEmailToSave);
        log.info("Saved : {} email to send", listOfEmailToSave.size());
    }

//    @Scheduled(cron = "0 30 10 * * *")
    @Transactional
    public void sendEmail() {
        emailSendingService.sendEmail();
        log.info("Send email");
    }
}
