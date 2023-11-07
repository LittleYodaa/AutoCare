package pl.patrykkawula.autocare.email;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {

    @Query(value = "SELECT * FROM email WHERE status = 'unsent'", nativeQuery = true)
    List<Email> findAllByStatusEqualsUnsent();
}
