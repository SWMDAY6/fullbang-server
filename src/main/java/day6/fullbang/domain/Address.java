package day6.fullbang.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "address",
    uniqueConstraints={
        @UniqueConstraint(
            columnNames={"latitude", "longitude"}
        )
    })
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private Place place;

    private String addressFullName;
    @Column(name = "region_1depth_name")
    private String region1DepthName;
    @Column(name = "region_2depth_name")
    private String region2DepthName;
    @Column(name = "region_3depth_name")
    private String region3DepthName;

    private String addressCode;
    private double latitude;
    private double longitude;

}
