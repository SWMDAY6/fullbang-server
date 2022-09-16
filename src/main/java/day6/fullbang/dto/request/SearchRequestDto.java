package day6.fullbang.dto.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestDto {

    // place entity
    private String keyword;
    private Double latitude;
    private Double longitude;

    // product entity
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkInDate;
}
