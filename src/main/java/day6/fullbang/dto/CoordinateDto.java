package day6.fullbang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordinateDto {
    private double longitudeStart;
    private double latitudeStart;

    private double longitudeEnd;
    private double latitudeEnd;
}
