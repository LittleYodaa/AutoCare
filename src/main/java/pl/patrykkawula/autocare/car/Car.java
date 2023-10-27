package pl.patrykkawula.autocare.car;

import jakarta.persistence.*;
import lombok.*;
import pl.patrykkawula.autocare.user.User;

import java.time.LocalDate;



@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private LocalDate productionDate;
    private LocalDate registrationDate;
    private Integer mileage;
    private Integer plannedAnnualMileage;
    private LocalDate technicalInspectionEndDate;
    private LocalDate insurenceEndDate;
    private LocalDate paymentRateDate;
    private LocalDate nextCarServiceDate;
    @ManyToOne
    private User user;

}
