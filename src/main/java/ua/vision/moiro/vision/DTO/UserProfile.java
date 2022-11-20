package ua.vision.moiro.vision.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserProfile {
    private String name;
    private String surname;
    private String email;
    private String phone;
}
