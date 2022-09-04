package day6.fullbang.dto.addressInfo;

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
        latitude = addressInfo.getLatitude();
        longitude = addressInfo.getLongitude();

        region1DepthName = addressInfo.getRegion1DepthName();
        region1DepthAddressCode = addressInfo.getAddressCode().getRegion1DepthAddressCode();

        if (addressInfo.getAddressCode().getRegion2DepthAddressCode() != null) {
            region2DepthName = addressInfo.getRegion2DepthName();
            region2DepthAddressCode = addressInfo.getAddressCode().getRegion2DepthAddressCode();
        } else {
            region2DepthName = "";
            region2DepthAddressCode = "";
        }

        if (addressInfo.getAddressCode().getRegion3DepthAddressCode() != null) {
            region3DepthName = addressInfo.getRegion3DepthName();
            region3DepthAddressCode = addressInfo.getAddressCode().getRegion3DepthAddressCode();
        } else {
            region3DepthName = "";
            region3DepthAddressCode = "";
        }
    }
}
