package pl.patrykkawula.autocare.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.patrykkawula.autocare.car.CarBrandModelView;
import pl.patrykkawula.autocare.user.dtos.UserDto;
import pl.patrykkawula.autocare.user.dtos.UserInfoDto;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
    public class UserController {
        private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    ResponseEntity<?> addUser(@RequestBody UserDto userSaveDto) {
        UserDto userDto = userService.saveUser(userSaveDto);
        URI savedUserUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDto.id())
                .toUri();
        log.info("Add new user {}", userDto);
        return ResponseEntity.created(savedUserUri).build();
    }

    @GetMapping("/{id}")
    ResponseEntity<UserInfoDto> findUser(@PathVariable Long id) {
        UserInfoDto foundUser = userService.findById(id);
        return ResponseEntity.ok(foundUser);
    }

    @GetMapping("/{id}/cars")
    ResponseEntity<List<CarBrandModelView>> getAllUserCars(@PathVariable Long id) {
        List<CarBrandModelView> allUserCars = userService.findCarsByUserId(id);
        return ResponseEntity.ok(allUserCars);
    }

    @PutMapping("/{id}")
    ResponseEntity<UserInfoDto> updateUser(@PathVariable Long id, @RequestBody UserInfoDto userInfoDto) {
        UserInfoDto updatedUser = userService.updateUser(id, userInfoDto);
        log.info("Update user with id {}", id);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
