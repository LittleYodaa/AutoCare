package pl.patrykkawula.autocare.user;

import org.springframework.stereotype.Service;
import pl.patrykkawula.autocare.car.CarBrandModel;
import pl.patrykkawula.autocare.car.CarDtoMapper;
import pl.patrykkawula.autocare.car.CarRepository;
import pl.patrykkawula.autocare.exception.UserNotFoundException;
import pl.patrykkawula.autocare.user.dtos.UserDto;
import pl.patrykkawula.autocare.user.dtos.UserInfoDto;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final CarDtoMapper carDtoMapper;
    private final CarRepository carRepository;

    public UserService(UserRepository userRepository, UserDtoMapper userDtoMapper, CarDtoMapper carDtoMapper, CarRepository carRepository) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
        this.carDtoMapper = carDtoMapper;
        this.carRepository = carRepository;
    }


    //todo
    //nie działa zliczanie aut użytkownika
    public void countUsersCar(Long id) {
        userRepository.findById(id).ifPresent(u -> u.setNumberOfCarsOwned(u.getCars().size()));
    }


    UserDto saveUser(UserDto userDto) {
        User userToSave = userDtoMapper.map(userDto);
        User savedUser = userRepository.save(userToSave);
        return userDtoMapper.mapToUserDto(savedUser);
    }

    public UserInfoDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return userDtoMapper.mapToUserInfoDto(user);
    }

    List<CarBrandModel> findCarsByUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return carRepository.findCarsByUserId(user.getId());
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
