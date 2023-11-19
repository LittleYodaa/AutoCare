package pl.patrykkawula.autocare.usageCost;

import jakarta.persistence.*;
import lombok.*;
import pl.patrykkawula.autocare.car.Car;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private CostType costType;
    private String description;
    private Double cost;
    @ManyToOne
    private Car car;

    public enum CostType{
        FUEL,
        SERVICE,
        INSURANCE,
        OTHER
    }

    //todo
    //zmienić domyślne ustawienie klas na package private z public

}
