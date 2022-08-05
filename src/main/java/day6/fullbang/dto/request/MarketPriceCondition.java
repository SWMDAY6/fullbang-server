package day6.fullbang.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MarketPriceCondition {

    private final String addressCodeHead;
    private final String placeType;
    private final String date;
    private final Integer capacity;
    private final Boolean parkingAvailability;
}
