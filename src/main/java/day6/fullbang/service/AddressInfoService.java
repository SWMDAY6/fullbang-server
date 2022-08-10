package day6.fullbang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import day6.fullbang.dto.addressInfo.AddressInfoDto;
import day6.fullbang.dto.request.CoordinateRangeDto;
import day6.fullbang.repository.AddressInfoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressInfoService {

    private final AddressInfoRepository addressInfoRepository;

    public List<AddressInfoDto> getAddressInfoByCoordinateRange(CoordinateRangeDto coordinateRangeDto,
        int regionDepth) {

        return addressInfoRepository.getAddressInfoByCoordinateRange(coordinateRangeDto.getLatitudeStart(),
            coordinateRangeDto.getLatitudeEnd(), coordinateRangeDto.getLongitudeStart(),
            coordinateRangeDto.getLongitudeEnd(), regionDepth);
    }
}
