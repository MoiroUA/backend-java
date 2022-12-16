package ua.vision.moiro.vision.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.vision.moiro.vision.model.Coordinates;
import ua.vision.moiro.vision.model.PolygonInfo;

import java.util.List;

public interface CoordinatesRepository extends JpaRepository<Coordinates, Integer> {
    List<Coordinates> findAllByPolygonInfo(PolygonInfo polygonInfo);
}
