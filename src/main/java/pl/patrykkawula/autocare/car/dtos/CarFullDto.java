package pl.patrykkawula.autocare.car.dtos;

import lombok.Builder;
import lombok.Data;
import pl.patrykkawula.autocare.user.User;

import java.time.LocalDate;

@Data
@Builder
public class CarFullDto {
    private Long id;
    private String brand;
    private String model;
    private LocalDate productionDate;
    private LocalDate registrationDate;
    private Integer mileage;
    private User user;
}
