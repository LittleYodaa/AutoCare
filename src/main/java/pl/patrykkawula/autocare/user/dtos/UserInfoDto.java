package pl.patrykkawula.autocare.user.dtos;

import lombok.Builder;

@Builder
public record UserInfoDto(
        String firstName,
        String lastName,
        String email,
        Integer numberOfCarsOwned) {
}
