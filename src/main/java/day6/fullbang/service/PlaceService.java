package day6.fullbang.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import day6.fullbang.domain.Place;
import day6.fullbang.domain.Product;
import day6.fullbang.domain.Room;
import day6.fullbang.dto.request.CoordinateRangeByDateDto;
import day6.fullbang.dto.request.FilterOptionRequestDto;
import day6.fullbang.dto.request.SearchRequestDto;
import day6.fullbang.dto.response.PlaceResponseDto;
import day6.fullbang.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public List<PlaceResponseDto> findPlacesByCoordinate(CoordinateRangeByDateDto coordinateRangeByDateDto) {
        List<Place> placesByCoordinate = placeRepository.findPlacesByCoordinate(coordinateRangeByDateDto);
        List<PlaceResponseDto> responseAllPlaces = placesEntityToDto(placesByCoordinate);

        return responseAllPlaces;
    }

    public List<PlaceResponseDto> findPlacesByOption(FilterOptionRequestDto filterOptionRequestDto) {
        List<Place> filteredPlaces = placeRepository.findPlacesByOption(filterOptionRequestDto);
        List<PlaceResponseDto> responseFilteredPlaces = placesEntityToDto(filteredPlaces);

        return responseFilteredPlaces;
    }

    public List<PlaceResponseDto> findPlacesByPlaceName(SearchRequestDto searchRequestDto) {
        List<Place> placesByPlaceName = placeRepository.findPlacesByPlaceName(searchRequestDto);
        List<PlaceResponseDto> responseSearchPlaces = placesEntityToDto(placesByPlaceName);

        return responseSearchPlaces;
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
        Long compareLowestPriceInPriceList = priceList.stream().min(Comparator.comparing(v -> v)).orElse(0L);

        return compareLowestPriceInPriceList;
    }

    public List<PlaceResponseDto> placesEntityToDto(List<Place> placesEntity) {
        List<PlaceResponseDto> placeResponseDto = new ArrayList<>();

        for (Place place : placesEntity) {
            Long lowestPrice = calculateLowestPriceInputDate(place.getRooms());
            PlaceResponseDto item = new PlaceResponseDto(place, lowestPrice);
            placeResponseDto.add(item);
        }

        return placeResponseDto;
    }
}
