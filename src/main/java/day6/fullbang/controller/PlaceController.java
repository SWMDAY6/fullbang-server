package day6.fullbang.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import day6.fullbang.domain.Place;
import day6.fullbang.domain.PlaceType;
import day6.fullbang.domain.Product;
import day6.fullbang.domain.Room;
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

        List<Place> placesByCoordinate = placeService.findPlacesByCoordinate(coordinateRangeByDateDto);

        List<PlaceResponseDto> responsePlaces = new ArrayList<>();
        for (Place place : placesByCoordinate) {
            Long lowestPrice = calculateLowestPriceInputDate(place.getRooms());
            PlaceResponseDto item = new PlaceResponseDto(place, lowestPrice);
            responsePlaces.add(item);
        }

        return responsePlaces;

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

        List<Place> filteredPlaces = placeService.findPlacesByOption(filterOptionRequestDto);
        List<PlaceResponseDto> filteredResponsePlaces = filteredPlaces.stream()
            .map(p -> new PlaceResponseDto(p, calculateLowestPriceInputDate(p.getRooms())))
            .collect(Collectors.toList());

        return filteredResponsePlaces;
    }

    @GetMapping("/search/{keyword}")
    public List<PlaceResponseDto> readPlacesByPlaceName(@PathVariable("keyword") String keyword,
        @RequestParam("latitude") Double latitude,
        @RequestParam("longitude") Double longitude,
        @RequestParam("inputDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inputDate) {
        SearchRequestDto searchRequestDto = new SearchRequestDto(keyword, latitude, longitude, inputDate);
        List<Place> placesByPlaceName = placeService.findPlacesByPlaceName(searchRequestDto);

        List<PlaceResponseDto> responsePlaces = new ArrayList<>();
        for (Place place : placesByPlaceName) {
            Long lowestPrice = calculateLowestPriceInputDate(place.getRooms());
            PlaceResponseDto item = new PlaceResponseDto(place, lowestPrice);
            responsePlaces.add(item);
        }

        return responsePlaces;
    }

    public Long calculateLowestPriceInputDate(List<Room> rooms) {
        List<Long> priceList = new ArrayList<>();
        // LocalDate nowDate = LocalDate.now(ZoneId.of("Asia/Seoul")); //현재 날짜

        for (Room room : rooms) {
            List<Product> products = room.getProducts();

            for (Product product : products) {
                if (product.getType().equals("숙박")) {
                    priceList.add(product.getPrice());
                }
            }
        }

        return priceList.stream().min(Comparator.comparing(v -> v)).orElse(0L);
    }

}
