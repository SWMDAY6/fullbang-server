package day6.fullbang.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import day6.fullbang.dto.product.PriceInfoDto;
import day6.fullbang.dto.response.MarketPriceDto;
import day6.fullbang.dto.request.MarketPriceCondition;
import day6.fullbang.util.MarketPriceCalculator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarketPriceService {

    private final ProductService productService;

    public MarketPriceDto getByAddressCode(MarketPriceCondition marketPriceCondition) {

        List<PriceInfoDto> targetProducts = productService.getPriceInfoByAddressCode(marketPriceCondition);

        List<Long> prices = new ArrayList<>();
        targetProducts.forEach(priceInfo -> prices.add(priceInfo.getPrice()));

        Double mean = MarketPriceCalculator.getMean(prices);
        Double confidenceIntervalOffset = MarketPriceCalculator.getConfidenceIntervalOffset(prices);

        return new MarketPriceDto(mean, mean - confidenceIntervalOffset, mean + confidenceIntervalOffset);
    }

}
