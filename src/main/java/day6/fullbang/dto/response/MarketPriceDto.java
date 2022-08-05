package day6.fullbang.dto.response;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MarketPriceDto {

    private final Double mean;
    private final Double minMeanOfRange;
    private final Double maxMeanOfRange;

}
