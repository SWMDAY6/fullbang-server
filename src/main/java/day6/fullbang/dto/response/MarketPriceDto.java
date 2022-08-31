package day6.fullbang.dto.response;

import day6.fullbang.domain.PlaceType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MarketPriceDto {

    private final Double mean;
    private final Double minMeanOfRange;
    private final Double maxMeanOfRange;
    private final PlaceType placeType;

}
