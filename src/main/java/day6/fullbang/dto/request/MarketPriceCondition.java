package day6.fullbang.dto.request;

import day6.fullbang.domain.PlaceType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MarketPriceCondition {

    private PlaceType placeType;
    private String date;
    private Integer capacity;
    private Boolean parkingAvailability;
}
