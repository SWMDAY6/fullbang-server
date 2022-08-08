package day6.fullbang.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/product/inRange/marketPrice")
    public List<MarketPriceDto> getMarketPriceByCoordinateRange(
        @RequestBody MarketPriceConditionDto marketPriceConditionDto,
        CoordinateRangeDto coordinateRangeDto) {

        return marketPriceService.getByCoordinateRange(marketPriceConditionDto, CoordinateRangeDto);
    }

}
