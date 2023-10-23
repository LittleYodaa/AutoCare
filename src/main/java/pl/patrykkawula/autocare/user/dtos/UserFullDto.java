package pl.patrykkawula.autocare.user.dtos;

import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;
import pl.patrykkawula.autocare.car.Car;

import java.util.List;

@Data
@Builder
public class UserFullDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer numberOfCarsOwned;
    private List<Car> car;

}
