package day6.fullbang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import day6.fullbang.dto.response.MarketPriceDto;
import day6.fullbang.dto.service.MarketPriceParamDto;
import day6.fullbang.service.MarketPriceService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MarketPriceController {

    private final MarketPriceService marketPriceService;

    @GetMapping("/market-price")
    public MarketPriceDto getMarketPriceByAddressCode(
        @RequestParam(name = "address-code-head") String addressCodeHead,
        @RequestParam(name = "place-type") String placeType,
        @RequestParam(name = "date") String date,
        @RequestParam(name = "capacity") Integer capacity,
        @RequestParam(name = "parking-availability") Boolean parkingAvailability) {

        MarketPriceParamDto marketPriceParamDto = new MarketPriceParamDto(addressCodeHead, placeType, date,
            capacity, parkingAvailability);

        marketPriceService.getByAddressCode(marketPriceParamDto);
    }
}
