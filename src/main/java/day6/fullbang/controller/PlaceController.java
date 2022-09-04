package day6.fullbang.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import day6.fullbang.domain.Place;
import day6.fullbang.domain.PlaceType;
import day6.fullbang.dto.request.CoordinateRangeDto;
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
        @RequestParam("latitudeEnd") Double latitudeEnd,
        @RequestParam("inputDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate) {
        CoordinateRangeDto coordinateRangeDto = new CoordinateRangeDto(longitudeStart, latitudeStart, longitudeEnd,
            latitudeEnd);

        List<Place> placesByCoordinate = placeService.findPlacesByCoordinate(coordinateRangeDto);

        List<PlaceResponseDto> responsePlaces = new ArrayList<>();
        for (Place place : placesByCoordinate) {
            PlaceResponseDto item = new PlaceResponseDto(place, localDate);
            responsePlaces.add(item);
        }

        return responsePlaces;

    }

    @GetMapping("/places/option")
    public List<PlaceResponseDto> readFilteredPlaces(
        @RequestParam("parkingAvailability") Boolean parkingAvailability,
        @RequestParam("placeType") PlaceType placeType,
        @RequestParam("maximumCapacity") Integer maximumCapacity,
        @RequestParam("inputDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inputDate) {
        FilterOptionRequestDto filterOptionRequestDto =
            new FilterOptionRequestDto(parkingAvailability, placeType, maximumCapacity, inputDate);

        List<Place> filteredPlaces = placeService.findPlacesByOption(filterOptionRequestDto);
        List<PlaceResponseDto> filteredResponsePlaces = filteredPlaces.stream()
            .map(p -> new PlaceResponseDto(p, inputDate))
            .collect(Collectors.toList());

        return filteredResponsePlaces;
    }

    @GetMapping("/search/{keyword}")
    public List<PlaceResponseDto> readPlacesByPlaceName(@PathVariable("keyword") String keyword,
        @RequestParam("inputDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inputDate) {
        List<Place> placesByPlaceName = placeService.findPlacesByPlaceName(keyword);

        List<PlaceResponseDto> responsePlaces = new ArrayList<>();
        for (Place place : placesByPlaceName) {
            PlaceResponseDto item = new PlaceResponseDto(place, inputDate);
            responsePlaces.add(item);
        }

        return responsePlaces;
    }

}
