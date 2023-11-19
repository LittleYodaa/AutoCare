package pl.patrykkawula.autocare.usageCost;

import org.springframework.stereotype.Service;
import pl.patrykkawula.autocare.car.Car;
import pl.patrykkawula.autocare.car.CarRepository;
import pl.patrykkawula.autocare.usageCost.dtos.NewCostDto;
import pl.patrykkawula.autocare.usageCost.dtos.CostViewDto;

@Service
class CostDtoMapper {
    private final CarRepository carRepository;

    CostDtoMapper(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    Cost map(NewCostDto newCostDto) {
        Car car = carRepository.findById(newCostDto.carId()).orElseThrow();
        return Cost.builder()
                .costType(newCostDto.costType())
                .description(newCostDto.description())
                .cost(newCostDto.cost())
                .car(car)
                .build();
    }

    CostViewDto mapToCostViewDto(Cost cost) {
        return CostViewDto.builder()
                .id(cost.getId())
                .costType(cost.getCostType())
                .description(cost.getDescription())
                .cost(cost.getCost())
                .build();
    }
}
