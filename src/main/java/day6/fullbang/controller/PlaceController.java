package day6.fullbang.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import day6.fullbang.domain.Place;
import day6.fullbang.dto.CoordinateRangeDto;
import day6.fullbang.dto.request.FilterOptionRequestDto;
import day6.fullbang.dto.response.PlaceResponseDto;
import day6.fullbang.service.PlaceService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping("/places")
    public List<PlaceResponseDto> readAllPlaces(@RequestBody CoordinateRangeDto coordinateRangeDto) {
        List<Place> placesByCoordinate = placeService.findPlacesByCoordinate(coordinateRangeDto);

        List<PlaceResponseDto> responsePlaces = new ArrayList<>();
        for (Place place : placesByCoordinate) {
            PlaceResponseDto item = new PlaceResponseDto(place);
            responsePlaces.add(item);
        }

        return responsePlaces;

    }

    @PostMapping("/places/option")
    public List<PlaceResponseDto> readFilteredPlaces(@RequestBody FilterOptionRequestDto filterOptionRequestDto) {
        List<Place> filteredPlaces = placeService.findPlacesByOption(filterOptionRequestDto);
        List<PlaceResponseDto> filteredResponsePlaces = filteredPlaces.stream()
                .map(p -> new PlaceResponseDto(p))
                .collect(Collectors.toList());

        return filteredResponsePlaces;
    }
}
