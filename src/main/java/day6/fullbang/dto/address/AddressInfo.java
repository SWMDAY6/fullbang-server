package day6.fullbang.dto.address;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddressInfo {

    private final String region1DepthName;
    private final String region2DepthName;
    private final String region3DepthName;
    private final String region1DepthAddressCode;
    private final String region2DepthAddressCode;
    private final String region3DepthAddressCode;
    private final Double latitude;
    private final Double longitude;
}
