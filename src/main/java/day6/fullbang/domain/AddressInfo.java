package day6.fullbang.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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

    @EmbeddedId
    private AddressCode addressCode;

    @Column(name = "region_1depth_name")
    private String region1DepthName;
    @Column(name = "region_2depth_name")
    private String region2DepthName;
    @Column(name = "region_3depth_name")
    private String region3DepthName;

    private Integer regionDepth;

    private Double latitude;
    private Double longitude;

    public String getAddressCodeHead(int regionDepth) {

        String result = addressCode.getRegion1DepthAddressCode();

        if (regionDepth == 1) {
            return result;
        }

        result += addressCode.getRegion2DepthAddressCode();

        if (regionDepth == 2) {
            return result;
        }

        result += addressCode.getRegion3DepthAddressCode();

        return result;
    }
}
