package pl.patrykkawula.autocare.usageCost.dtos;

import lombok.Builder;
import pl.patrykkawula.autocare.usageCost.Cost;

@Builder
public record CostViewDto(
        Long id,
        Cost.CostType costType,
        String description,
        Double cost
) {
}