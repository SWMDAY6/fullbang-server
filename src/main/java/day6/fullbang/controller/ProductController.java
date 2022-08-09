package day6.fullbang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import day6.fullbang.domain.PlaceType;
import day6.fullbang.dto.request.MarketPriceConditionDto;
import day6.fullbang.dto.response.MarketPriceDto;
import day6.fullbang.service.MarketPriceService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final MarketPriceService marketPriceService;

    @GetMapping("/product/{addressCodeHead}/marketPrice")
    public MarketPriceDto getMarketPriceByAddressCode(@RequestParam PlaceType placeType, @RequestParam String date,
        @RequestParam Integer capacity, @RequestParam Boolean parkingAvailability,
        @PathVariable(name = "addressCodeHead") String addressCodeHead) {

        MarketPriceConditionDto marketPriceConditionDto = new MarketPriceConditionDto(placeType, date, capacity,
            parkingAvailability);

        return marketPriceService.getByAddressCode(marketPriceConditionDto, addressCodeHead);
    }
}
