package pl.patrykkawula.autocare.email;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmailRepository extends JpaRepository<Email, Long> {
    List<Email> findAllByStatus(Email.Status status);

    //todo
    //enkapsulacja
}
