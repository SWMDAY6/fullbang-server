package day6.fullbang.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;

@Getter
@Embeddable
public class AddressCode implements Serializable {

    @Column(name = "region_1depth_code")
    private String region1DepthAddressCode;

    @Column(name = "region_2depth_code")
    private String region2DepthAddressCode;

    @Column(name = "region_3depth_code")
    private String region3DepthAddressCode;
}
