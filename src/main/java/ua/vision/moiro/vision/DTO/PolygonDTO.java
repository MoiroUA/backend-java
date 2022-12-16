package ua.vision.moiro.vision.DTO;

import lombok.*;

import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PolygonDTO {
    private Double kef;
    private List<CoordinatesDTO> coordinates;
}
