package pl.patrykkawula.autocare.user;

import org.springframework.stereotype.Service;
import pl.patrykkawula.autocare.exception.UserNotFoundException;
import pl.patrykkawula.autocare.user.dtos.UserDto;
import pl.patrykkawula.autocare.user.dtos.UserInfoDto;

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


    UserDto saveUser(UserDto userDto) {
        User userToSave = userDtoMapper.map(userDto);
        User savedUser = userRepository.save(userToSave);
        return userDtoMapper.mapToUserDto(savedUser);
    }

    UserInfoDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return userDtoMapper.mapToUserInfoDto(user);
    }

    UserInfoDto updateUser(Long id, UserInfoDto userInfoDto) {
        User userToSave = userDtoMapper.map(userInfoDto);
        userToSave.setId(id);
        User savedUser = userRepository.save(userToSave);
        return userDtoMapper.mapToUserInfoDto(savedUser);
    }

    void deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }
}
