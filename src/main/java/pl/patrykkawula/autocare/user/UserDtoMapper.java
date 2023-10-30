package pl.patrykkawula.autocare.user;

import org.springframework.stereotype.Service;
import pl.patrykkawula.autocare.user.dtos.UserDto;
import pl.patrykkawula.autocare.user.dtos.UserInfoDto;

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

    User map(UserInfoDto userInfoDto) {
        return User.builder()
                .firstName(userInfoDto.firstName())
                .lastName(userInfoDto.lastName())
                .email(userInfoDto.email())
                .numberOfCarsOwned(userInfoDto.numberOfCarsOwned())
                .build();
    }

    UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .numberOfCarsOwned(user.getNumberOfCarsOwned())
                .build();
    }

    UserInfoDto mapToUserInfoDto(User user) {
        return UserInfoDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .numberOfCarsOwned(user.getNumberOfCarsOwned())
                .build();
    }
}
