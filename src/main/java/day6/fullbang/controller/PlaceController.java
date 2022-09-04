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
        @RequestParam("inputDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inputDate) {
        CoordinateRangeDto coordinateRangeDto = new CoordinateRangeDto(longitudeStart, latitudeStart, longitudeEnd,
            latitudeEnd);

        List<Place> placesByCoordinate = placeService.findPlacesByCoordinate(coordinateRangeDto);

        List<PlaceResponseDto> responsePlaces = new ArrayList<>();
        for (Place place : placesByCoordinate) {
            Long lowestPrice = calculateLowestPriceInputDate(place.getRooms(), inputDate);
            PlaceResponseDto item = new PlaceResponseDto(place, lowestPrice);
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
            .map(p -> new PlaceResponseDto(p, calculateLowestPriceInputDate(p.getRooms(), inputDate)))
            .collect(Collectors.toList());

        return filteredResponsePlaces;
    }

    @GetMapping("/search/{keyword}")
    public List<PlaceResponseDto> readPlacesByPlaceName(@PathVariable("keyword") String keyword,
        @RequestParam("inputDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inputDate) {
        List<Place> placesByPlaceName = placeService.findPlacesByPlaceName(keyword);

        List<PlaceResponseDto> responsePlaces = new ArrayList<>();
        for (Place place : placesByPlaceName) {
            Long lowestPrice = calculateLowestPriceInputDate(place.getRooms(), inputDate);
            PlaceResponseDto item = new PlaceResponseDto(place, lowestPrice);
            responsePlaces.add(item);
        }

        return responsePlaces;
    }

    public Long calculateLowestPriceInputDate(List<Room> rooms, LocalDate inputDate) {
        List<Long> priceList = new ArrayList<>();
        // LocalDate nowDate = LocalDate.now(ZoneId.of("Asia/Seoul")); //현재 날짜

        for (Room room : rooms) {
            List<Product> products = room.getProducts();

            for (Product product : products) {
                if ((product.getCheckInDateTime().toLocalDate().isEqual(inputDate)) && (product.getType()
                    .equals("숙박"))) {
                    priceList.add(product.getPrice());
                }
            }
        }

        return priceList.stream().min(Comparator.comparing(v -> v)).orElse(0L);
    }

}
