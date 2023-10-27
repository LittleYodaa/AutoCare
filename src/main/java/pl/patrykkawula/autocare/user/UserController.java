package pl.patrykkawula.autocare.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.patrykkawula.autocare.user.Exception.UserNotExistException;
import pl.patrykkawula.autocare.user.dtos.UserDto;

import java.net.URI;

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
        return ResponseEntity.created(savedUserUri).build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
        } catch (UserNotExistException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
