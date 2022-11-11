package day6.fullbang.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LongSummaryStatistics;

import org.springframework.stereotype.Service;

import day6.fullbang.domain.Image;
import day6.fullbang.domain.Platform;
import day6.fullbang.domain.Product;
import day6.fullbang.domain.Room;
import day6.fullbang.dto.product.PriceDateInfoDto;
import day6.fullbang.dto.response.RoomResponseDto;
import day6.fullbang.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomService {

    private final RoomRepository roomRepository;

    public List<Room> findRoomsByPlaceId(Long placeId) {
        return roomRepository.findRoomsByPlaceId(placeId);
    }

    public List<RoomResponseDto> convertRoomIntoRoomDto(List<Room> roomsByPlaceId, LocalDate checkInDate) {

        List<RoomResponseDto> roomResponseDtoList = new ArrayList<>(); // 반환할 DTO

        for (Room room : roomsByPlaceId) {
            RoomResponseDto roomResponseDto = new RoomResponseDto();
            roomResponseDto.setRoomId(room.getId());
            roomResponseDto.setRoomName(room.getName());

            // image
            List<Image> imageList = roomRepository.findImageByRoomId(room.getId());
            List<String> imageUrlList = new ArrayList<>();
            for (Image image : imageList) {
                String imageUrl = image.getUrl();
                imageUrlList.add(imageUrl);
            }
            roomResponseDto.setImgUrl(imageUrlList);

            // Product
            List<Product> products = room.getProducts();
            List<PriceDateInfoDto> stayPriceListAll = new ArrayList<>(); // 전체 숙박 Dto list
            List<PriceDateInfoDto> stayPriceListYanolja = new ArrayList<>(); // 야놀자 숙박 Dto List
            List<PriceDateInfoDto> stayPriceListYeogieottae = new ArrayList<>(); // 여기어때 숙박 Dto List

            for (Product product : products) {

                if (product.getType().contains("숙박")) {
                    stayPriceListAll.add(new PriceDateInfoDto(product.getPrice(), product.getPlatform(),
                        product.getType(), product.getCheckInDateTime().toLocalDate()));
                    if (product.getPlatform().equals(Platform.YANOLJA)) {
                        stayPriceListYanolja.add(new PriceDateInfoDto(product.getPrice(), product.getPlatform(),
                            product.getType(), product.getCheckInDateTime().toLocalDate()));
                    } else {
                        stayPriceListYeogieottae.add(new PriceDateInfoDto(product.getPrice(), product.getPlatform(),
                            product.getType(), product.getCheckInDateTime().toLocalDate()));
                    }

                }
                setPlatformPrice(product, checkInDate, roomResponseDto);

            }
            roomResponseDto.setStayPriceListYanolja(stayPriceListYanolja);
            roomResponseDto.setStayPriceListYeogieottae(stayPriceListYeogieottae);

            // 평일 평균 가격, 주말 평균 가격 구하기(숙박 기준)
            Double[] averagePriceArray = calculateAveragePrice(stayPriceListAll);

            roomResponseDto.setWeekdayStayAveragePrice(averagePriceArray[0]);
            roomResponseDto.setWeekendStayAveragePrice(averagePriceArray[1]);

            roomResponseDtoList.add(roomResponseDto);
        }

        return roomResponseDtoList;
    }

    public void setPlatformPrice(Product product, LocalDate checkInDate, RoomResponseDto roomResponseDto) {
        Platform platform = product.getPlatform();

        if (!checkInDate.isEqual(product.getCheckInDateTime().toLocalDate())) {
            return;
        }
        if (platform.equals(Platform.YANOLJA) && product.getType().contains("숙박")) {
            roomResponseDto.setStayPriceYanolja(product.getPrice());
            roomResponseDto.setUrlYanolja(product.getUrl());
        } else if (platform.equals(Platform.YANOLJA) && (product.getType().contains("대실") ||
            product.getType().equals("DayUse"))) {
            roomResponseDto.setTimePriceYanolja(product.getPrice());
            roomResponseDto.setUrlYanolja(product.getUrl());
        } else if (platform.equals(Platform.YEOGIEOTTAE) && product.getType().contains("숙박")) {
            roomResponseDto.setStayPriceYeogieottae(product.getPrice());
            roomResponseDto.setUrlYeogieottae(product.getUrl());
        } else if (platform.equals(Platform.YEOGIEOTTAE) && product.getType().contains("대실")) {
            roomResponseDto.setTimePriceYeogieottae(product.getPrice());
            roomResponseDto.setUrlYeogieottae(product.getUrl());
        }
    }

    public Double[] calculateAveragePrice(List<PriceDateInfoDto> stayPriceList) {

        List<Long> weekdayPriceList = new ArrayList<>();
        List<Long> weekendPriceList = new ArrayList<>();

        // 평일 List

        List<Integer> weekdayIntList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        for (PriceDateInfoDto priceDateInfoDto : stayPriceList) {
            DayOfWeek dayOfWeek = priceDateInfoDto.getCheckInDate().getDayOfWeek();

            Integer dayOfWeekNumber = dayOfWeek.getValue();

            if (weekdayIntList.contains(dayOfWeekNumber)) {
                weekdayPriceList.add(priceDateInfoDto.getPrice());
            } else {
                weekendPriceList.add(priceDateInfoDto.getPrice());
            }
        }
        Double weekdayStayAveragePrice = getAverage(weekdayPriceList);
        Double weekendStayAveragePrice = getAverage(weekendPriceList);

        return new Double[] {weekdayStayAveragePrice, weekendStayAveragePrice};
    }

    private static double getAverage(List<Long> list) {
        LongSummaryStatistics stats = list.stream()
            .mapToLong(Long::intValue)
            .summaryStatistics();
        return stats.getAverage();
    }

}
