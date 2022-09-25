package day6.fullbang.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import day6.fullbang.domain.PlaceType;
import day6.fullbang.dto.request.CoordinateRangeByDateDto;
import day6.fullbang.dto.request.FilterOptionRequestDto;
import day6.fullbang.dto.request.SearchRequestDto;
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
        @RequestParam("inputDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inputDate) {
        CoordinateRangeByDateDto coordinateRangeByDateDto = new CoordinateRangeByDateDto(longitudeStart, latitudeStart,
            longitudeEnd,
            latitudeEnd, inputDate);

        List<PlaceResponseDto> responseAllPlaces = placeService.findPlacesByCoordinate(coordinateRangeByDateDto);

        return responseAllPlaces;

    }

    @GetMapping("/places/option")
    public List<PlaceResponseDto> readFilteredPlaces(
        @RequestParam("longitudeStart") Double longitudeStart,
        @RequestParam("latitudeStart") Double latitudeStart,
        @RequestParam("longitudeEnd") Double longitudeEnd,
        @RequestParam("latitudeEnd") Double latitudeEnd,
        @RequestParam("inputDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inputDate,
        @RequestParam(value = "parkingAvailability", required = false)
        Boolean parkingAvailability,
        @RequestParam(value = "placeType", required = false)
        PlaceType placeType,
        @RequestParam(value = "maximumCapacity", required = false)
        Integer maximumCapacity) {
        FilterOptionRequestDto filterOptionRequestDto =
            new FilterOptionRequestDto(parkingAvailability, placeType, longitudeStart, latitudeStart, longitudeEnd,
                latitudeEnd, maximumCapacity, inputDate);

        List<PlaceResponseDto> responseFilteredPlaces = placeService.findPlacesByOption(filterOptionRequestDto);

        return responseFilteredPlaces;
    }

    @GetMapping("/search/{keyword}")
    public List<PlaceResponseDto> readPlacesByPlaceName(@PathVariable("keyword") String keyword,
        @RequestParam("latitude") Double latitude,
        @RequestParam("longitude") Double longitude,
        @RequestParam("inputDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inputDate) {
        SearchRequestDto searchRequestDto = new SearchRequestDto(keyword, latitude, longitude, inputDate);

        List<PlaceResponseDto> responseSearchPlaces = placeService.findPlacesByPlaceName(searchRequestDto);

        return responseSearchPlaces;
    }

}
