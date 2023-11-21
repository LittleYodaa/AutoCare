package pl.patrykkawula.autocare.usageCost.mapperAndConverter;

import org.springframework.stereotype.Service;
import pl.patrykkawula.autocare.car.Car;
import pl.patrykkawula.autocare.car.CarRepository;
import pl.patrykkawula.autocare.usageCost.Cost;
import pl.patrykkawula.autocare.usageCost.dtos.NewCostDto;
import pl.patrykkawula.autocare.usageCost.dtos.CostViewDto;
import pl.patrykkawula.autocare.usageCost.dtos.PdfCostDto;

@Service
public class CostDtoMapper {
    private final CarRepository carRepository;

    CostDtoMapper(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Cost map(NewCostDto newCostDto) {
        Car car = carRepository.findById(newCostDto.carId()).orElseThrow();
        return Cost.builder()
                .costType(newCostDto.costType())
                .description(newCostDto.description())
                .cost(newCostDto.cost())
                .dateOfAdd(newCostDto.dateOfAdd())
                .car(car)
                .build();
    }

    public CostViewDto mapToCostViewDto(Cost cost) {
        return CostViewDto.builder()
                .id(cost.getId())
                .costType(cost.getCostType())
                .description(cost.getDescription())
                .cost(cost.getCost())
                .dateOfAdd(cost.getDateOfAdd())
                .build();
    }

    public PdfCostDto mapToPdfCostDto(Cost cost) {
        return PdfCostDto.builder()
                .costType(cost.getCostType())
                .description(cost.getDescription())
                .cost(cost.getCost())
                .dateOfAdd(cost.getDateOfAdd())
                .build();
    }
}
