package pl.patrykkawula.autocare;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.patrykkawula.autocare.car.CarRepository;

@Service
public class ScheduledTasks {

    private final CarRepository carRepository;

    public ScheduledTasks(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Transactional
    @Scheduled(cron = "0 10 10 * * *")
    public void addPlannedMileage() {
        carRepository.findAll().forEach(c -> c.setMileage(c.getMileage() + (c.getPlannedAnnualMileage()/365)));
    }
}
