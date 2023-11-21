package pl.patrykkawula.autocare.usageCost.dtos;

import lombok.Builder;
import pl.patrykkawula.autocare.usageCost.Cost;

import java.time.LocalDate;

@Builder
public record PdfCostDto(
    Cost.CostType costType,
    String description,
    Double cost,
    LocalDate dateOfAdd,
    LocalDate startDate,
    LocalDate endDate
) {
}
