package day6.fullbang.dto.response;

import day6.fullbang.domain.Address;
import day6.fullbang.domain.Place;
import day6.fullbang.domain.PlaceType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlaceResponseDto {
    private Long placeId;
    private String placeName;
    private PlaceType placeType; // (모텔, 호텔, 게스트하우스 등)
    private Long lowestPrice; // 당일 최저가 가격(여기어때, 야놀자)
    private String placeImage; // 숙박업체 이미지
    private String addressFullName; // 주소 풀네임
    private String region1DepthName; // 시/도
    private String region2DepthName; // 시/군/구
    private String region3DepthName; // 읍/면/동
    private String addressCode; // 지역코드
    private Double latitude; // 위도
    private Double longitude; // 경도

    public PlaceResponseDto(Place place, Long lowestPrice) {
        this.placeId = place.getId();
        this.placeName = place.getName();
        this.placeType = place.getType();
        this.placeImage = place.getRepresentative_image();

        this.lowestPrice = lowestPrice;

        Address address = place.getAddress();
        this.addressFullName = address.getAddressFullName();
        this.region1DepthName = address.getRegion1DepthName();
        this.region2DepthName = address.getRegion2DepthName();
        this.region3DepthName = address.getRegion3DepthName();
        this.addressCode = address.getAddressCode();
        this.latitude = address.getLatitude();
        this.longitude = address.getLongitude();
    }

}