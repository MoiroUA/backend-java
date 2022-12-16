package ua.vision.moiro.vision.api;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.CrossOrigin;
import ua.vision.moiro.vision.DTO.LoginDto;
import ua.vision.moiro.vision.DTO.SignUpDto;
import ua.vision.moiro.vision.DTO.UserProfile;
import ua.vision.moiro.vision.exception.UserExistsInApp;
import ua.vision.moiro.vision.model.User;
import ua.vision.moiro.vision.security.jwt.JwtTokenProvider;
import ua.vision.moiro.vision.service.UserService;


import java.util.Base64;
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

    @CrossOrigin
    @PostMapping(path = "/register")
    public ResponseEntity<Map<String, String>> registrationUser(@RequestBody SignUpDto signUpDto) {
        User user = userService.registrationUser(signUpDto);
        Map<String, String> response = new HashMap<>();
        response.put("email", user.getEmail());
        return new ResponseEntity<>(response, CREATED);
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody LoginDto loginDto) {
        try {
            String email = loginDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, loginDto.getPassword()));
            User user = userService.findByEmail(email);

            if (user == null) {
                throw new UserExistsInApp(email);
            }
            Map<String, String> response = new HashMap<>();
            String token = jwtTokenProvider.createToken(email, user.getRoles());
            response.put("token", token);

            return new ResponseEntity<>(response, OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @CrossOrigin
    @GetMapping("/profile")
    public ResponseEntity<UserProfile> getInformationAboutUser(@RequestHeader Map<String, String> request) {
        String token = request.get("authorization");
        String secret = Base64.getEncoder().encodeToString("visionapp".getBytes());
        String email = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();

        User user = userService.findByEmail(email);
        return new ResponseEntity<>(UserProfile.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build(), OK);
    }
}

