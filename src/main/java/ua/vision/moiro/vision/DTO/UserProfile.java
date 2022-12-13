package ua.vision.moiro.vision.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class UserProfile {
    private String name;
    private String surname;
    private String email;
    private String phone;
}
