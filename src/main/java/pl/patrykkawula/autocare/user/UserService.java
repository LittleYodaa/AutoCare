package pl.patrykkawula.autocare.user;

import org.springframework.stereotype.Service;
import pl.patrykkawula.autocare.user.Exception.UserNotExistException;
import pl.patrykkawula.autocare.user.dtos.UserDto;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public UserService(UserRepository userRepository, UserDtoMapper userDtoMapper) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
    }

    public void countUsersCar(Long id) {
        userRepository.findById(id).ifPresent(u -> u.setNumberOfCarsOwned(u.getCars().size()));
    }


    UserDto saveUser(UserDto userSaveDto) {
        User userToSave = userDtoMapper.map(userSaveDto);
        User savedUser = userRepository.save(userToSave);
        return userDtoMapper.map(savedUser);
    }

    void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotExistException(id));
        userRepository.delete(user);
    }
}
