package day6.fullbang.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable
@Getter
public class Address {

    private String addressFullName;

    @Column(name = "region_1depth_name")
    private String region1DepthName;

    @Column(name = "region_2depth_name")
    private String region2DepthName;

    @Column(name = "region_3depth_name")
    private String region3DepthName;

    private String addressCode;

    private Double latitude;

    private Double longitude;
}
