package day6.fullbang.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import day6.fullbang.domain.MarketPrice;
import day6.fullbang.dto.addressInfo.AddressInfoDto;
import day6.fullbang.dto.product.PriceInfoDto;
import day6.fullbang.dto.request.CoordinateRangeDto;
import day6.fullbang.dto.request.MarketPriceConditionDto;
import day6.fullbang.dto.response.MarketPriceDto;
import day6.fullbang.repository.MarketPriceRepository;
import day6.fullbang.util.MarketPriceCalculator;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MarketPriceService {

    private final ProductService productService;
    private final AddressInfoService addressInfoService;
    private final MarketPriceRepository marketPriceRepository;

    public MarketPriceDto getByAddressCode(MarketPriceConditionDto marketPriceConditionDto, String addressCodeHead) {

        MarketPrice cachedMarketPrice = marketPriceRepository.getMarketPriceByAddressCode(addressCodeHead);
        if (cachedMarketPrice != null) {

            return new MarketPriceDto(cachedMarketPrice.getMean(), cachedMarketPrice.getMinMeanOfRange(),
                cachedMarketPrice.getMaxMeanOfRange(), cachedMarketPrice.getPlaceType(),
                addressInfoService.getByAddressCode(addressCodeHead));
        }

        List<PriceInfoDto> targetProducts = productService.getPriceInfoByAddressCode(marketPriceConditionDto,
            addressCodeHead);

        List<Long> prices = new ArrayList<>();
        targetProducts.forEach(priceInfo -> prices.add(priceInfo.getPrice()));

        Double mean = MarketPriceCalculator.getMean(prices);
        Double confidenceIntervalOffset = MarketPriceCalculator.getConfidenceIntervalOffset(prices);

        AddressInfoDto addressInfoDto = addressInfoService.getByAddressCode(addressCodeHead);

        MarketPriceDto result = new MarketPriceDto(mean, mean - confidenceIntervalOffset,
            mean + confidenceIntervalOffset,
            marketPriceConditionDto.getPlaceType(), addressInfoDto);

        marketPriceRepository.save(result);

        return result;
    }

    public List<MarketPriceDto> getByCoordinateRange(MarketPriceConditionDto marketPriceConditionDto,
        CoordinateRangeDto coordinateRangeDto, int regionDepth) {

        AddressInfos addressInfos = new AddressInfos(
            addressInfoService.getAddressInfoByCoordinateRange(coordinateRangeDto, regionDepth));

        List<MarketPriceDto> resultList = new ArrayList<>();

        addressInfos.getAddressInfoDtos().forEach(
            addressInfo -> {
                resultList.add(getByAddressCode(marketPriceConditionDto, addressInfo.getAddressCodeHead()));
            });

        return resultList;
    }
}
