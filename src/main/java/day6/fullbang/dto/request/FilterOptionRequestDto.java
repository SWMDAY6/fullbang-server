package day6.fullbang.dto.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import day6.fullbang.domain.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FilterOptionRequestDto {

    // place entity
    private Boolean parkingAvailability;
    private PlaceType placeType;

    private Double longitudeStart;
    private Double latitudeStart;
    private Double longitudeEnd;
    private Double latitudeEnd;

    // room entity
    private Integer maximumCapacity;

    // product entity
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkInDate;

}
