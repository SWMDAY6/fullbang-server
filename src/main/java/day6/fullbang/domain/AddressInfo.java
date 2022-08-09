package day6.fullbang.domain;

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

    public String getAddressCodeHead(int regionDepth) {

        String result = region1DepthAddressCode;

        if (regionDepth == 1) {
            return result;
        }

        result += region2DepthAddressCode;

        if (regionDepth == 2) {
            return result;
        }

        result += region3DepthAddressCode;

        return result;
    }
}
