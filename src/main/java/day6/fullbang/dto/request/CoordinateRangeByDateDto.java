package day6.fullbang.dto.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CoordinateRangeByDateDto {

    // place entity
    private Double longitudeStart;
    private Double latitudeStart;

    private Double longitudeEnd;
    private Double latitudeEnd;

    // product entity
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkInDate;
}
