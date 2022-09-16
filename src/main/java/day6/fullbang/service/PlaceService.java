package day6.fullbang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import day6.fullbang.domain.Place;
import day6.fullbang.dto.request.CoordinateRangeByDateDto;
import day6.fullbang.dto.request.FilterOptionRequestDto;
import day6.fullbang.dto.request.SearchRequestDto;
import day6.fullbang.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public List<Place> findPlacesByCoordinate(CoordinateRangeByDateDto coordinateRangeByDateDto) {
        return placeRepository.findPlacesByCoordinate(coordinateRangeByDateDto);
    }

    public List<Place> findPlacesByOption(FilterOptionRequestDto filterOptionRequestDto) {
        return placeRepository.findPlacesByOption(filterOptionRequestDto);
    }

    public List<Place> findPlacesByPlaceName(SearchRequestDto searchRequestDto) {
        return placeRepository.findPlacesByPlaceName(searchRequestDto);
    }

}
