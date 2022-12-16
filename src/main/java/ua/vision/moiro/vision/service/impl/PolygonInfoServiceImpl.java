package ua.vision.moiro.vision.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.vision.moiro.vision.DTO.CoordinatesDTO;
import ua.vision.moiro.vision.DTO.PolygonDTO;
import ua.vision.moiro.vision.model.Coordinates;
import ua.vision.moiro.vision.model.PolygonInfo;
import ua.vision.moiro.vision.repository.CoordinatesRepository;
import ua.vision.moiro.vision.repository.PolygonInfoRepository;
import ua.vision.moiro.vision.service.PolygonInfoService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PolygonInfoServiceImpl implements PolygonInfoService {
    private final PolygonInfoRepository polygonInfoRepository;
    private final CoordinatesRepository coordinatesRepository;

    @Autowired
    public PolygonInfoServiceImpl(PolygonInfoRepository polygonInfoRepository,
                                  CoordinatesRepository coordinatesRepository) {
        this.polygonInfoRepository = polygonInfoRepository;
        this.coordinatesRepository = coordinatesRepository;
    }

    @Override
    public List<PolygonDTO> getAll() {
        List<PolygonInfo> polygonInfoList = polygonInfoRepository.findAll();
        List<PolygonDTO> polygonDTOList = new ArrayList<>();

        for (PolygonInfo polygonInfo: polygonInfoList) {
           var listOfCoordinates = coordinatesRepository.findAllByPolygonInfo(polygonInfo);
           List<CoordinatesDTO> listResult = new ArrayList<>();
           for(Coordinates coordinates: listOfCoordinates) {
               CoordinatesDTO dto = new CoordinatesDTO(coordinates.getLatitude(), coordinates.getLongitude());
               listResult.add(dto);
           }
           polygonDTOList.add(PolygonDTO.builder()
                           .kef(polygonInfo.getKef())
                           .coordinates(listResult)
                           .build());
        }
        return polygonDTOList;
    }
}
