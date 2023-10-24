package pl.patrykkawula.autocare.user.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer numberOfCarsOwned;
}
