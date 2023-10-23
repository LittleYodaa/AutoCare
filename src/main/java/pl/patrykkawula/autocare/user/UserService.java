package pl.patrykkawula.autocare.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    void countUsersCar(Long id) {
        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers) {
            user.setNumberOfCarsOwned(user.getCar().size());
        }
    }




}
