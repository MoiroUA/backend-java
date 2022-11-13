package ua.vision.moiro.vision.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.vision.moiro.vision.model.PolygonInfo;
import ua.vision.moiro.vision.repository.PolygonInfoRepository;
import ua.vision.moiro.vision.service.PolygonInfoService;

import java.util.List;

@Service
public class PolygonInfoServiceImpl implements PolygonInfoService {
    private final PolygonInfoRepository polygonInfoRepository;

    @Autowired
    public PolygonInfoServiceImpl(PolygonInfoRepository polygonInfoRepository) {
        this.polygonInfoRepository = polygonInfoRepository;
    }

    @Override
    public List<PolygonInfo> getAll() {
        return polygonInfoRepository.findAll();
    }
}
