package pl.patrykkawula.autocare.user;

import org.springframework.stereotype.Service;
import pl.patrykkawula.autocare.user.dtos.UserInfoDto;
import pl.patrykkawula.autocare.user.dtos.UserSaveDto;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public UserService(UserRepository userRepository, UserDtoMapper userDtoMapper) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
    }

    public void countUsersCar(Long id) {
        userRepository.findById(id).ifPresent(u -> u.setNumberOfCarsOwned(u.getCar().size()));
    }


    UserInfoDto saveUser(UserSaveDto userSaveDto) {
        User userToSave = userDtoMapper.map(userSaveDto);
        User savedUser = userRepository.save(userToSave);
        return userDtoMapper.map(savedUser);
    }
}
