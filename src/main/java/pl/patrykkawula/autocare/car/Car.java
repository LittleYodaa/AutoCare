package pl.patrykkawula.autocare.car;

import jakarta.persistence.*;
import lombok.*;
import pl.patrykkawula.autocare.usageCost.Cost;
import pl.patrykkawula.autocare.user.User;

import java.time.LocalDate;
import java.util.List;


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
    private LocalDate insuranceEndDate;
    private LocalDate paymentRateDate;
    private LocalDate nextCarServiceDate;
    @ManyToOne
    private User user;
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "car_id")
    private List<Cost> costs;

}
