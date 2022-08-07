package day6.fullbang.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import day6.fullbang.domain.Place;
import day6.fullbang.dto.CoordinateRangeDto;
import day6.fullbang.dto.response.PlaceResponseDto;
import day6.fullbang.service.PlaceService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/places")
    public List<PlaceResponseDto> readAllPlaces(@RequestBody CoordinateRangeDto coordinateRangeDto) {
        List<Place> placesByCoordinate = placeService.findPlacesByCoordinate(coordinateRangeDto);

        List<PlaceResponseDto> responsePlaces = new ArrayList<>();
        for (Place place : placesByCoordinate) {
            PlaceResponseDto item = new PlaceResponseDto(place);
            responsePlaces.add(item);
        }

        return responsePlaces;

    }

    @GetMapping("/search/{keyword}")
    public List<PlaceResponseDto> readPlacesByPlaceName(@PathVariable("keyword") String keyword) {
        List<Place> placesByPlaceName = placeService.findPlacesByPlaceName(keyword);

        List<PlaceResponseDto> responsePlaces = new ArrayList<>();
        for (Place place : placesByPlaceName) {
            PlaceResponseDto item = new PlaceResponseDto(place);
            responsePlaces.add(item);
        }

        return responsePlaces;
    }
}
