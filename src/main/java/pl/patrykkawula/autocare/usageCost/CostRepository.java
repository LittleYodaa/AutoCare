package pl.patrykkawula.autocare.usageCost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.patrykkawula.autocare.user.dtos.CostView;

import java.util.List;

@Repository
interface CostRepository extends JpaRepository<Cost, Long> {
    List<CostView> getAllByCarId(Long carId);
}
