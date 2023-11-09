package pl.patrykkawula.autocare.email;

import org.springframework.stereotype.Service;
import pl.patrykkawula.autocare.car.CarRepository;
import pl.patrykkawula.autocare.car.CarService;
import pl.patrykkawula.autocare.car.dtos.CarDto;
import pl.patrykkawula.autocare.user.User;
import pl.patrykkawula.autocare.user.UserDtoMapper;
import pl.patrykkawula.autocare.user.UserService;

import java.time.LocalDate;
import java.util.List;
@Service
public class PrepareTechnicalInspectionEmail implements PrepareEmail{
    private final static int SEVEN_DAYS_TO_DATE = 7;
    private final CarRepository carRepository;
    private final UserService userService;
    private final CarService carService;
    private final UserDtoMapper userDtoMapper;

    public PrepareTechnicalInspectionEmail(CarRepository carRepository, UserService userService, CarService carService, UserDtoMapper userDtoMapper) {
        this.carRepository = carRepository;
        this.userService = userService;
        this.carService = carService;
        this.userDtoMapper = userDtoMapper;
    }

    @Override
    public List<Long> findCarWithIncomingEvent() {
        return carRepository.findIncomingTechnicalServiceDate(LocalDate.now().plusDays(SEVEN_DAYS_TO_DATE));
    }

    public List<Email> createListOfEmail() {
        return findCarWithIncomingEvent().stream()
                .map(this::createEmail)
                .toList();
    }

    private Email createEmail(Long carId) {
        CarDto carDto = carService.findById(carId);
        Email email = new Email();
        email.setSubject("Badanie techniczne");
        email.setText(String.format("Twoje badanie techniczne straci ważność za %s dni.", SEVEN_DAYS_TO_DATE));
        email.setEmailRecipient(userService.findById(carDto.userId()).email());
        email.setStatus(Email.Status.UNSENT);
        email.setUser(userDtoMapper.map(userService.findById(carDto.userId())));
        return email;
    }
}


