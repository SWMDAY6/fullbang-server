package day6.fullbang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import day6.fullbang.domain.Place;
import day6.fullbang.dto.CoordinateRangeDto;
import day6.fullbang.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    public List<Place> findPlacesByCoordinate(CoordinateRangeDto coordinateRangeDto) {
        return placeRepository.findPlacesByCoordinate(coordinateRangeDto);
    }
}
