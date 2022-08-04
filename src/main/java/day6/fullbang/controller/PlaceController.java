package day6.fullbang.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import day6.fullbang.domain.Place;
import day6.fullbang.dto.CoordinateDto;
import day6.fullbang.dto.response.PlaceResponseDto;
import day6.fullbang.service.PlaceService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PlaceController {

	private final PlaceService placeService;

	@GetMapping("/places")
	public List<PlaceResponseDto> readAllPlaces(@RequestBody CoordinateDto coordinateDto) {
		List<Place> placesByCoordinate = placeService.findPlacesByCoordinate(coordinateDto);

		List<PlaceResponseDto> responsePlaces = new ArrayList<>();
		for (Place place : placesByCoordinate) {
			PlaceResponseDto item = new PlaceResponseDto(place);
			responsePlaces.add(item);
		}

		return responsePlaces;

	}

}
