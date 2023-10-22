package pl.patrykkawula.autocare.user.dtos;

import jakarta.persistence.OneToMany;
import pl.patrykkawula.autocare.car.Car;

import java.util.List;

public class UserFullDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

}
