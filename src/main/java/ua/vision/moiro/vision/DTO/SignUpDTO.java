package ua.vision.moiro.vision.DTO;

import lombok.Data;

@Data
public class SignUpDTO {
    private final String name;
    private final String surname;
    private final String email;
    private final String password;
}
