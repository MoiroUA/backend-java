package ua.vision.moiro.vision.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.vision.moiro.vision.model.PolygonInfo;

@Repository
public interface PolygonInfoRepository extends JpaRepository<PolygonInfo, Integer> {

}
