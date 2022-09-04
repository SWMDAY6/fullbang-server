package day6.fullbang.dto.response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import day6.fullbang.domain.Address;
import day6.fullbang.domain.Place;
import day6.fullbang.domain.PlaceType;
import day6.fullbang.domain.Product;
import day6.fullbang.domain.Room;
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

    public PlaceResponseDto(Place place, LocalDate inputDate) {
        this.placeId = place.getId();
        this.placeName = place.getName();
        this.placeType = place.getType();
        this.placeImage = place.getRepresentative_image();

        this.lowestPrice = calculateLowestPriceInputDate(place.getRooms(), inputDate);

        Address address = place.getAddress();
        this.addressFullName = address.getAddressFullName();
        this.region1DepthName = address.getRegion1DepthName();
        this.region2DepthName = address.getRegion2DepthName();
        this.region3DepthName = address.getRegion3DepthName();
        this.addressCode = address.getAddressCode();
        this.latitude = address.getLatitude();
        this.longitude = address.getLongitude();
    }

    public Long calculateLowestPriceInputDate(List<Room> rooms, LocalDate inputDate) {
        List<Long> priceList = new ArrayList<>();
        // LocalDate nowDate = LocalDate.now(ZoneId.of("Asia/Seoul")); //현재 날짜

        for (Room room : rooms) {
            List<Product> products = room.getProducts();

            for (Product product : products) {
                if ((product.getCheckInDateTime().toLocalDate().isEqual(inputDate)) && (product.getType()
                    .equals("숙박"))) {
                    priceList.add(product.getPrice());
                }
            }
        }

        return priceList.stream().min(Comparator.comparing(v -> v)).orElse(0L);
    }

}