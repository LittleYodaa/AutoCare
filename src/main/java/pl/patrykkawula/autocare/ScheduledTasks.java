package pl.patrykkawula.autocare;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.patrykkawula.autocare.car.CarRepository;
import pl.patrykkawula.autocare.car.CarService;
import pl.patrykkawula.autocare.car.dtos.CarDto;
import pl.patrykkawula.autocare.user.UserService;

import java.time.LocalDate;

@Service
public class ScheduledTasks {
    private static final int ONE_DAY_TO_DATE = 1;
    private static final int SEVEN_DAYS_TO_DATE = 7;
    private static final String TECHNICAL_INSPECTION = "technical inspection";
    private static final String INSURANCE = "insurance";
    private static final String PAYMENT_RATE_DATE = "payment rate";
    private static final String SERVICE = "service";
    private final CarRepository carRepository;
    private final CarService carService;
    private final UserService userService;

    public ScheduledTasks(CarRepository carRepository, CarService carService, UserService userService) {
        this.carRepository = carRepository;
        this.carService = carService;
        this.userService = userService;
    }

    @Transactional
    @Scheduled(cron = "0 10 10 * * *")
    public void addPlannedMileage() {
        carRepository.findAll().forEach(c -> c.setMileage(c.getMileage() + (c.getPlannedAnnualMileage() / 365)));
    }

    private void chechTechnicalInspectionDate(LocalDate localDate) {
        Long carId = carRepository.carId(localDate.plusDays(SEVEN_DAYS_TO_DATE));
        CarDto carDto = carService.findById(carId);
        String userEmail = userService.findById(carDto.userId()).email();
    }
    
    private boolean checkDate(LocalDate localDate, int daysToDeadline, String event) {
        LocalDate dateBeforeDeadline = localDate.minusDays(daysToDeadline);
        LocalDate currentDate = LocalDate.now();
        return dateBeforeDeadline.isEqual(currentDate);
    }
}
