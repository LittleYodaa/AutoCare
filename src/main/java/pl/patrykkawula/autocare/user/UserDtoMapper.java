package pl.patrykkawula.autocare.user;

import org.springframework.stereotype.Service;
import pl.patrykkawula.autocare.user.dtos.UserInfoDto;
import pl.patrykkawula.autocare.user.dtos.UserSaveDto;

@Service
public class UserDtoMapper {

    UserInfoDto map(User user) {
        return UserInfoDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .numberOfCarsOwned(user.getNumberOfCarsOwned())
                .build();
    }

    User map(UserInfoDto userInfoDto) {
        return User.builder()
                .id(userInfoDto.getId())
                .firstName(userInfoDto.getFirstName())
                .lastName(userInfoDto.getLastName())
                .email(userInfoDto.getEmail())
                .numberOfCarsOwned(userInfoDto.getNumberOfCarsOwned())
                .build();
    }

    User map(UserSaveDto userSaveDto) {
        return User.builder()
                .firstName(userSaveDto.getFirstName())
                .lastName(userSaveDto.getLastName())
                .email(userSaveDto.getEmail())
                .numberOfCarsOwned(userSaveDto.getNumberOfCarsOwned())
                .build();
    }
}
