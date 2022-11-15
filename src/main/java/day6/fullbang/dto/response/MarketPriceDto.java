package day6.fullbang.dto.response;

import day6.fullbang.domain.PlaceType;
import day6.fullbang.dto.addressInfo.AddressInfoDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MarketPriceDto {

    private final Double mean;
    private final Double minMeanOfRange;
    private final Double maxMeanOfRange;
    private final PlaceType placeType;
    private final AddressInfoDto addressInfoDto;
}
