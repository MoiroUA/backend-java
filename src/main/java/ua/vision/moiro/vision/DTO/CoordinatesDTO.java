package ua.vision.moiro.vision.DTO;


import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class CoordinatesDTO {
    private Double latitude;
    private Double longitude;
}
