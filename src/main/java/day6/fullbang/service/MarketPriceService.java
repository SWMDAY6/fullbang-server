package day6.fullbang.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import day6.fullbang.dto.product.PriceInfoDto;
import day6.fullbang.dto.request.CoordinateRangeDto;
import day6.fullbang.dto.request.MarketPriceConditionDto;
import day6.fullbang.dto.response.MarketPriceDto;
import day6.fullbang.util.MarketPriceCalculator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarketPriceService {

    private final ProductService productService;

    public MarketPriceDto getByAddressCode(MarketPriceConditionDto marketPriceConditionDto, String addressCodeHead) {

        List<PriceInfoDto> targetProducts = productService.getPriceInfoByAddressCode(marketPriceConditionDto,
            addressCodeHead);

        List<Long> prices = new ArrayList<>();
        targetProducts.forEach(priceInfo -> prices.add(priceInfo.getPrice()));

        Double mean = MarketPriceCalculator.getMean(prices);
        Double confidenceIntervalOffset = MarketPriceCalculator.getConfidenceIntervalOffset(prices);

        return new MarketPriceDto(mean, mean - confidenceIntervalOffset, mean + confidenceIntervalOffset);
    }

    public List<MarketPriceDto> getByCoordinateRange(MarketPriceConditionDto marketPriceConditionDto,
        CoordinateRangeDto coordinateRangeDto, int regionDepth) {

        List<AddressInfo> addressInfos = BCodeUtil.getAddressInfoByRange(coordinateRangeDto.getLatitudeStart(),
            coordinateRangeDto.getLatitudeEnd(), coordinateRangeDto.getLongitudeStart(),
            coordinateRangeDto.getLongitudeEnd());

        List<MarketPriceDto> resultList = new ArrayList<>();

        addressInfos.forEach(
            addressInfo -> resultList.add(getByAddressCode(addressInfo.getAddressCodeHead(regionDepth))));

        return resultList;
    }
}
