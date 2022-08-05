package day6.fullbang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import day6.fullbang.dto.response.MarketPriceDto;
import day6.fullbang.dto.service.MarketPriceCondition;
import day6.fullbang.service.MarketPriceService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final MarketPriceService marketPriceService;

    @GetMapping("/product/{addressCodeHead}/market-price")
    public MarketPriceDto getMarketPriceByAddressCode(@RequestParam MarketPriceCondition marketPriceCondition) {

        marketPriceService.getByAddressCode(marketPriceCondition);
    }
}
