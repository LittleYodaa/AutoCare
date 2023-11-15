package pl.patrykkawula.autocare.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.patrykkawula.autocare.email.IncomingEmailView;

import java.time.LocalDate;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "SELECT brand AS brand, model AS model FROM car WHERE user_id = :id",
            nativeQuery = true)
    List<CarBrandModel> findCarsByUserId(Long id);

    List<IncomingEmailView> getAllByTechnicalInspectionEndDate(LocalDate technicalInspectionEndDate);

    List<IncomingEmailView> getAllByInsuranceEndDate(LocalDate insuranceEndDate);

    List<IncomingEmailView> getAllByNextCarServiceDate(LocalDate serviceEndDate);

    List<IncomingEmailView> getAllByPaymentRateDate(LocalDate paymentRateEndDate);
}
