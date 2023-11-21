package pl.patrykkawula.autocare.usageCost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CostRepository extends JpaRepository<Cost, Long>, CostRepositoryCustom {
    List<Cost> getAllByCarId(Long carId);

}