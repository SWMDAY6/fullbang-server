package day6.fullbang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import day6.fullbang.dto.addressInfo.AddressInfoDto;
import day6.fullbang.dto.request.CoordinateRangeDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressInfoService {

    private final AddressInfoRepository addressInfoRepository;

    public static List<AddressInfoDto> getAddressInfoByCoordinateRange(CoordinateRangeDto coordinateRangeDto) {

        return addressInfoRepository.getAddressInfoByCoordinateRange(coordinateRangeDto.getLatitudeStart(),
            coordinateRangeDto.getLatitudeEnd(), coordinateRangeDto.getLongitudeStart(),
            coordinateRangeDto.getLongitudeEnd());
    }
}
