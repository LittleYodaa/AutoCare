package pl.patrykkawula.autocare.user.dtos;

import lombok.Builder;

@Builder
public record UserDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        Integer numberOfCarsOwned) {
}
