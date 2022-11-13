package ua.vision.moiro.vision.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;
import ua.vision.moiro.vision.model.Coordinates;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "polygon_info")
public class PolygonInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @Transient
    private List<Coordinates> coordinates;

    @Column
    private Double kef;
}
