package day6.fullbang.dto.product;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import day6.fullbang.domain.Platform;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PriceDateInfoDto {
	private Long price;

	private Platform platform;

	private String productType;  // 숙박, 대실

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private LocalDate checkInDate;

}
