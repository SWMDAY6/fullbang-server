package day6.fullbang.dto.request;

import day6.fullbang.domain.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MarketPriceConditionDto {

    private PlaceType placeType;
    private String date;
    private Integer capacity;
    private Boolean parkingAvailability;
}
