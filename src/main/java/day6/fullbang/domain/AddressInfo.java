package day6.fullbang.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "address_info")
@AllArgsConstructor
@NoArgsConstructor
public class AddressInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_info_id")
    private Long id;

    @Column(name = "region_1depth_name")
    private String region1DepthName;
    @Column(name = "region_2depth_name")
    private String region2DepthName;
    @Column(name = "region_3depth_name")
    private String region3DepthName;
    @Column(name = "region_1depth_code")
    private String region1DepthAddressCode;
    @Column(name = "region_2depth_code")
    private String region2DepthAddressCode;
    @Column(name = "region_3depth_code")
    private String region3DepthAddressCode;
    private Double latitude;
    private Double longitude;

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
