package ua.vision.moiro.vision.DTO;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserProfile {
    private String name;
    private String surname;
    private String email;
    private String phone;
}
