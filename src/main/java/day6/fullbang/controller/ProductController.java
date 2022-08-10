package day6.fullbang.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import day6.fullbang.domain.PlaceType;
import day6.fullbang.dto.request.CoordinateRangeDto;
import day6.fullbang.dto.request.MarketPriceConditionDto;
import day6.fullbang.dto.response.MarketPriceDto;
import day6.fullbang.service.MarketPriceService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final MarketPriceService marketPriceService;

    @GetMapping("/product/{addressCodeHead}/marketPrice")
    public MarketPriceDto getMarketPriceByAddressCode(@RequestBody MarketPriceConditionDto marketPriceConditionDto,
        @PathVariable(name = "addressCodeHead") String addressCodeHead) {

        return marketPriceService.getByAddressCode(marketPriceConditionDto, addressCodeHead);
    }

    @GetMapping("/product/inRange/marketPrice/{regionDepth}")
    public List<MarketPriceDto> getMarketPriceByCoordinateRange(
        @RequestParam PlaceType placeType, @RequestParam String date,
        @RequestParam Integer capacity, @RequestParam Boolean parkingAvailability,
        @RequestParam Double latitudeStart,
        @RequestParam Double latitudeEnd,
        @RequestParam Double longitudeStart,
        @RequestParam Double longitudeEnd,
        @PathVariable int regionDepth) {

        MarketPriceConditionDto marketPriceConditionDto = new MarketPriceConditionDto(placeType, date, capacity,
            parkingAvailability);

        CoordinateRangeDto coordinateRangeDto = new CoordinateRangeDto(longitudeStart, latitudeStart, longitudeEnd,
            latitudeEnd);

        return marketPriceService.getByCoordinateRange(marketPriceConditionDto, coordinateRangeDto, regionDepth);
    }

}
