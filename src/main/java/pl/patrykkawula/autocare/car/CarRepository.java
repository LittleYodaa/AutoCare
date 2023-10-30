package pl.patrykkawula.autocare.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "SELECT car.brand AS brand, car.model AS model FROM car WHERE user_id = id",
            nativeQuery = true)
    List<CarBrandModel> findCarsByUserId(@Param("id" ) Long id);

}
