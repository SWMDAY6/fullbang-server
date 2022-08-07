package day6.fullbang.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MarketPriceDto {

    private final Double mean;
    private final Double minMeanOfRange;
    private final Double maxMeanOfRange;

}
