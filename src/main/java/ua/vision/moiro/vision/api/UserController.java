package ua.vision.moiro.vision.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.vision.moiro.vision.model.User;
import ua.vision.moiro.vision.service.UserService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> userList = userService.findAll();
        return new ResponseEntity<>(userList, OK);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<User> getById(@PathVariable("userId") Integer userId) {
        User user = userService.findById(userId);
        return new ResponseEntity<>(user, OK);
    }

    @GetMapping(path = "/email")
    public ResponseEntity<User> getById(@RequestBody String email) {
        Optional<User> user = userService.findAll()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst();
        return new ResponseEntity<>(user.get(), OK);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<User> createUser(@RequestBody User entity) {
        User user = userService.createUser(entity);
        return new ResponseEntity<>(user, CREATED);
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<?> updateUserById(@RequestBody User entity, @PathVariable Integer userId) {
        userService.updateUserById(entity, userId);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(OK);
    }
}

