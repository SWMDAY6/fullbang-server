package day6.fullbang.dto.addressInfo;

import javax.persistence.Column;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddressInfoDto {

    private final String region1DepthName;
    private final String region2DepthName;
    private final String region3DepthName;
    private final String region1DepthAddressCode;
    private final String region2DepthAddressCode;
    private final String region3DepthAddressCode;
    private final Double latitude;
    private final Double longitude;
}
