package pl.patrykkawula.autocare.car;


import org.springframework.stereotype.Service;
import pl.patrykkawula.autocare.car.dtos.CarFullDto;
import pl.patrykkawula.autocare.car.dtos.CarSaveDto;
import pl.patrykkawula.autocare.user.User;
import pl.patrykkawula.autocare.user.UserRepository;

@Service
public class CarDtoMapper {
    private final UserRepository userRepository;

    public CarDtoMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Car map(CarFullDto carFullDto) {
        return new Car
                (carFullDto.getId(),
                        carFullDto.getBrand(),
                        carFullDto.getModel(),
                        carFullDto.getProductionDate(),
                        carFullDto.getRegistrationDate(),
                        carFullDto.getMileage(),
                        carFullDto.getUser());
    }

    public CarFullDto map(Car car) {
        return CarFullDto.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .productionDate(car.getProductionDate())
                .registrationDate(car.getRegistrationDate())
                .mileage(car.getMileage())
                .user(car.getUser())
                .build();
    }

    public Car map (CarSaveDto carSaveDto) {
        User user = userRepository.findById(carSaveDto.getUserId()).orElseThrow();
        return Car.builder()
                .brand(carSaveDto.getBrand())
                .model(carSaveDto.getModel())
                .productionDate(carSaveDto.getProductionDate())
                .registrationDate(carSaveDto.getRegistrationDate())
                .mileage(carSaveDto.getMileage())
                .user(user)
                .build();
    }

    public CarSaveDto mapToSave (Car car) {
        return CarSaveDto.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .productionDate(car.getProductionDate())
                .registrationDate(car.getRegistrationDate())
                .mileage(car.getMileage())
                .userId(car.getUser().getId())
                .build();
    }
}
