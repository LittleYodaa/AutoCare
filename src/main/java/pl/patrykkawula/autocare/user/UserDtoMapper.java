package pl.patrykkawula.autocare.user;

import org.springframework.stereotype.Service;
import pl.patrykkawula.autocare.user.dtos.UserDto;

@Service
public class UserDtoMapper {

    User map(UserDto userDto) {
        return User.builder()
                .firstName(userDto.firstName())
                .lastName(userDto.lastName())
                .email(userDto.email())
                .numberOfCarsOwned(userDto.numberOfCarsOwned())
                .build();
    }

    UserDto map(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .numberOfCarsOwned(user.getNumberOfCarsOwned())
                .build();
    }
}
