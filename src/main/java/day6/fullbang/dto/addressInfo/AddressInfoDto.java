package day6.fullbang.dto.addressInfo;

import javax.persistence.Column;

import day6.fullbang.domain.AddressInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddressInfoDto {

    private final String addressCodeHead;
    private final String region1DepthName;
    private final String region2DepthName;
    private final String region3DepthName;
    private final String region1DepthAddressCode;
    private final String region2DepthAddressCode;
    private final String region3DepthAddressCode;
    private final Double latitude;
    private final Double longitude;

    public AddressInfoDto(AddressInfo addressInfo, int regionDepth) {
        addressCodeHead = addressInfo.getAddressCodeHead(regionDepth);
        region1DepthName = addressInfo.getRegion1DepthName();
        region2DepthName = addressInfo.getRegion2DepthName();
        region3DepthName = addressInfo.getRegion3DepthName();
        region1DepthAddressCode = addressInfo.getAddressCode().getRegion1DepthAddressCode();
        region2DepthAddressCode = addressInfo.getAddressCode().getRegion2DepthAddressCode();
        region3DepthAddressCode = addressInfo.getAddressCode().getRegion3DepthAddressCode();
        latitude = addressInfo.getLatitude();
        longitude = addressInfo.getLongitude();
    }
}
