package pl.patrykkawula.autocare.user;


import jakarta.persistence.*;
import lombok.*;
import pl.patrykkawula.autocare.car.Car;
import pl.patrykkawula.autocare.email.Email;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer numberOfCarsOwned;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Car> cars;
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private List<Email> emails;


}
