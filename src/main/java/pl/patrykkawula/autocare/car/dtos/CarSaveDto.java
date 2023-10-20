package pl.patrykkawula.autocare.car.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CarSaveDto {
    private Long id;
    private String brand;
    private String model;
    private LocalDate productionDate;
    private LocalDate registrationDate;
    private Integer mileage;
    private Long userId;
}