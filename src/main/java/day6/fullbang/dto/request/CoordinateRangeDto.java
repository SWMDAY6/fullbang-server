package day6.fullbang.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CoordinateRangeDto {
    private Double longitudeStart;
    private Double latitudeStart;

    private Double longitudeEnd;
    private Double latitudeEnd;
}
