package day6.fullbang.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import day6.fullbang.domain.Place;
import day6.fullbang.domain.PlaceType;
import day6.fullbang.dto.CoordinateRangeDto;
import day6.fullbang.dto.request.FilterOptionRequestDto;
import day6.fullbang.dto.response.PlaceResponseDto;
import day6.fullbang.service.PlaceService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/places")
    public List<PlaceResponseDto> readAllPlaces(
            @RequestParam("longitudeStart") Double longitudeStart,
            @RequestParam("latitudeStart") Double latitudeStart,
            @RequestParam("longitudeEnd") Double longitudeEnd,
            @RequestParam("latitudeEnd") Double latitudeEnd) {
        CoordinateRangeDto coordinateRangeDto = new CoordinateRangeDto(longitudeStart, latitudeStart, longitudeEnd,
                latitudeEnd);

        List<Place> placesByCoordinate = placeService.findPlacesByCoordinate(coordinateRangeDto);

        List<PlaceResponseDto> responsePlaces = new ArrayList<>();
        for (Place place : placesByCoordinate) {
            PlaceResponseDto item = new PlaceResponseDto(place);
            responsePlaces.add(item);
        }

        return responsePlaces;

    }

    @GetMapping("/places/option")
    public List<PlaceResponseDto> readFilteredPlaces(
            @RequestParam("parkingAvailability") Boolean parkingAvailability,
            @RequestParam("placeType") PlaceType placeType,
            @RequestParam("maximumCapacity") Integer maximumCapacity,
            @RequestParam("checkInDateTime") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkInDateTime) {
        FilterOptionRequestDto filterOptionRequestDto =
                new FilterOptionRequestDto(parkingAvailability, placeType, maximumCapacity, checkInDateTime);

        List<Place> filteredPlaces = placeService.findPlacesByOption(filterOptionRequestDto);
        List<PlaceResponseDto> filteredResponsePlaces = filteredPlaces.stream()
                .map(p -> new PlaceResponseDto(p))
                .collect(Collectors.toList());

        return filteredResponsePlaces;
    }
}
