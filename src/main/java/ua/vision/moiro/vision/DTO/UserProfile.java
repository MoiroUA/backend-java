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
    private final String name;
    private final String surname;
    private final String email;
    private final String phone;
}
