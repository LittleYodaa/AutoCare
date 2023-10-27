package pl.patrykkawula.autocare.user.dtos;

import lombok.Builder;
import pl.patrykkawula.autocare.user.User;

@Builder
public record UserDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        Integer numberOfCarsOwned) {
}
