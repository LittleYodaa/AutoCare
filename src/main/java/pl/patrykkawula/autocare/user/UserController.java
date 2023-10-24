package pl.patrykkawula.autocare.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.patrykkawula.autocare.user.dtos.UserInfoDto;
import pl.patrykkawula.autocare.user.dtos.UserSaveDto;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    ResponseEntity<?> addUser(@RequestBody UserSaveDto userSaveDto) {
        UserInfoDto userInfoDto = userService.saveUser(userSaveDto);
        URI savedUserUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userInfoDto.getId())
                .toUri();
        return ResponseEntity.created(savedUserUri).build();
    }
}
