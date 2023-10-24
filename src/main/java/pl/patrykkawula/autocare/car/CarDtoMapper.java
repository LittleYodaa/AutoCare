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
        return Car.builder()
                .id(carFullDto.getId())
                .brand(carFullDto.getBrand())
                .model(carFullDto.getModel())
                .productionDate(carFullDto.getProductionDate())
                .registrationDate(carFullDto.getRegistrationDate())
                .mileage(carFullDto.getMileage())
                .plannedAnnualMileage(carFullDto.getPlannedAnnualMileage())
                .technicalInspectionEndDate(carFullDto.getTechnicalInspectionEndDate())
                .insurenceEndDate(carFullDto.getInsurenceEndDate())
                .paymentRateDate(carFullDto.getPaymentRateDate())
                .nextCarServiceDate(carFullDto.getNextCarServiceDate())
                .user(carFullDto.getUser())
                .build();
    }

    public CarFullDto map(Car car) {
        return CarFullDto.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .productionDate(car.getProductionDate())
                .registrationDate(car.getRegistrationDate())
                .mileage(car.getMileage())
                .plannedAnnualMileage(car.getPlannedAnnualMileage())
                .technicalInspectionEndDate(car.getTechnicalInspectionEndDate())
                .insurenceEndDate(car.getInsurenceEndDate())
                .paymentRateDate(car.getPaymentRateDate())
                .nextCarServiceDate(car.getNextCarServiceDate())
                .user(car.getUser())
                .build();
    }

    public Car mapToSave (CarSaveDto carSaveDto) {
        User user = userRepository.findById(carSaveDto.getUserId()).orElseThrow();
        return Car.builder()
                .brand(carSaveDto.getBrand())
                .model(carSaveDto.getModel())
                .productionDate(carSaveDto.getProductionDate())
                .registrationDate(carSaveDto.getRegistrationDate())
                .mileage(carSaveDto.getMileage())
                .plannedAnnualMileage(carSaveDto.getPlannedAnnualMileage())
                .technicalInspectionEndDate(carSaveDto.getTechnicalInspectionEndDate())
                .insurenceEndDate(carSaveDto.getInsurenceEndDate())
                .paymentRateDate(carSaveDto.getPaymentRateDate())
                .nextCarServiceDate(carSaveDto.getNextCarServiceDate())
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
                .plannedAnnualMileage(car.getPlannedAnnualMileage())
                .technicalInspectionEndDate(car.getTechnicalInspectionEndDate())
                .insurenceEndDate(car.getInsurenceEndDate())
                .paymentRateDate(car.getPaymentRateDate())
                .nextCarServiceDate(car.getNextCarServiceDate())
                .userId(car.getUser().getId())
                .build();
    }
}
