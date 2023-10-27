package pl.patrykkawula.autocare.car.dtos;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CarDto(
        Long id,
        String brand,
        String model,
        LocalDate productionDate,
        LocalDate registrationDate,
        Integer mileage,
        Integer plannedAnnualMileage,
        LocalDate technicalInspectionEndDate,
        LocalDate insurenceEndDate,
        LocalDate paymentRateDate,
        LocalDate nextCarServiceDate,
        Long userId
) {
}
