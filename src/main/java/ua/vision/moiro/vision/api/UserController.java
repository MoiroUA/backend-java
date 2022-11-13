package ua.vision.moiro.vision.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import ua.vision.moiro.vision.DTO.LoginDto;
import ua.vision.moiro.vision.DTO.SignUpDto;
import ua.vision.moiro.vision.DTO.UserProfile;
import ua.vision.moiro.vision.exception.UserExistsInApp;
import ua.vision.moiro.vision.model.User;
import ua.vision.moiro.vision.security.jwt.JwtTokenProvider;
import ua.vision.moiro.vision.service.UserService;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> registrationUser(@RequestBody SignUpDto signUpDto) {
        User user = userService.registrationUser(signUpDto);
        return new ResponseEntity<>(CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        try {
            String email = loginDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, loginDto.getPassword()));
            User user = userService.findByEmail(email);

            if (user == null) {
                throw new UserExistsInApp(email);
            }

            String token = jwtTokenProvider.createToken(email, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", email);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

}

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserProfile> getInformationAboutUser(@PathVariable Integer id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(UserProfile.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build(), OK);
    }
}

