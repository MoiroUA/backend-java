package ua.vision.moiro.vision.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coordinates {
    private Double latitude;
    private Double longitude;
}
