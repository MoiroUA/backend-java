package ua.vision.moiro.vision.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SignUpDto {
    private String name;
    private String surname;
    private String email;
    private String password;
}
