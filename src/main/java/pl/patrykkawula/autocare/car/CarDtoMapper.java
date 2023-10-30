package pl.patrykkawula.autocare.car;


import org.springframework.stereotype.Service;
import pl.patrykkawula.autocare.car.dtos.CarDto;
import pl.patrykkawula.autocare.user.User;
import pl.patrykkawula.autocare.user.UserRepository;

@Service
public class CarDtoMapper {
    private final UserRepository userRepository;

    public CarDtoMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Car map(CarDto carDto) {
        User user = userRepository.findById(carDto.userId()).orElseThrow();
        return Car.builder()
                .brand(carDto.brand())
                .model(carDto.model())
                .productionDate(carDto.productionDate())
                .registrationDate(carDto.registrationDate())
                .mileage(carDto.mileage())
                .plannedAnnualMileage(carDto.plannedAnnualMileage())
                .technicalInspectionEndDate(carDto.technicalInspectionEndDate())
                .insuranceEndDate(carDto.insuranceEndDate())
                .paymentRateDate(carDto.paymentRateDate())
                .nextCarServiceDate(carDto.nextCarServiceDate())
                .user(user)
                .build();
    }

    public CarDto mapToCarDto(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .productionDate(car.getProductionDate())
                .registrationDate(car.getRegistrationDate())
                .mileage(car.getMileage())
                .plannedAnnualMileage(car.getPlannedAnnualMileage())
                .technicalInspectionEndDate(car.getTechnicalInspectionEndDate())
                .insuranceEndDate(car.getInsuranceEndDate())
                .paymentRateDate(car.getPaymentRateDate())
                .nextCarServiceDate(car.getNextCarServiceDate())
                .userId(car.getUser().getId())
                .build();
    }
}
