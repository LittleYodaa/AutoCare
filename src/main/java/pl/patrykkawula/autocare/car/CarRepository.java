package pl.patrykkawula.autocare.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "SELECT brand AS brand, model AS model FROM car WHERE user_id = :id",
            nativeQuery = true)
    List<CarBrandModel> findCarsByUserId(Long id);



    //todo
    //Check 1
    @Query(value = "SELECT id FROM car WHERE technical_inspection_end_date = :localDate",
            nativeQuery = true)
    Long findIncomingTechnicaServiceDate(LocalDate localDate);

}
