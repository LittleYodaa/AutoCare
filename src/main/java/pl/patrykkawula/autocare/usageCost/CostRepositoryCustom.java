package pl.patrykkawula.autocare.usageCost;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CostRepositoryCustom {
    List<Cost> findCostByCostTypeAndDate(Long id, String costType, LocalDate startDate, LocalDate endDate);
}
