package day6.fullbang.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceRequestDto {
	private double longitude_start;
	private double latitude_start;

	private double longitude_end;
	private double latitude_end;
}
