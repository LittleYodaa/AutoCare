package pl.patrykkawula.autocare.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.patrykkawula.autocare.car.Car;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

}
