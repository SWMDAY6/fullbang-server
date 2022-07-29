package day6.fullbang.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PriceInfoDto {
    private final String placeName;
    private final String roomName;
    private final Long price;
    private final LocalDate date;
}
