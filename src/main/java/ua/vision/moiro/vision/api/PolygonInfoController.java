package ua.vision.moiro.vision.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.vision.moiro.vision.model.PolygonInfo;
import ua.vision.moiro.vision.service.PolygonInfoService;

import java.util.List;

@RestController
@RequestMapping(value = "/areas")
public class PolygonInfoController {
    private final PolygonInfoService polygonInfoService;

    @Autowired
    public PolygonInfoController(PolygonInfoService polygonInfoService) {
        this.polygonInfoService = polygonInfoService;
    }

    @GetMapping
    public ResponseEntity<List<PolygonInfo>> getAll() {
        List<PolygonInfo> polygonInfoList = polygonInfoService.getAll();
        return new ResponseEntity<>(polygonInfoList, HttpStatus.OK);
    }
}
