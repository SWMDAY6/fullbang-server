package day6.fullbang.dto.response;

import java.util.List;

import day6.fullbang.dto.product.PriceDateInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponseDto {
    private Long roomId;
    private String roomName;

    // 당일
    private Long stayPriceYanolja; // 숙박 야놀자 가격
    private Long stayPriceYeogieottae; // 숙박 여기어때 가격
    private Long timePriceYanolja; // 대실 야놀자 가격
    private Long timePriceYeogieottae; // 대실 여기어때 가격

    private Double weekdayStayAveragePrice; // 평일 평균 가격
    private Double weekendStayAveragePrice; // 주말 평균 가격

    private List<String> imgUrl; // 방 이미지 리스트

    // 시계열 데이터
    private List<PriceDateInfoDto> StayPriceListYanolja; // 야놀자 숙박 가격 리스트
    private List<PriceDateInfoDto> StayPriceListYeogieottae; // 여기어때 숙박 가격 리스트

    private String urlYanolja; // 야놀자 예약 사이트
    private String urlYeogieottae; // 여기어때 예약 사이트

}
