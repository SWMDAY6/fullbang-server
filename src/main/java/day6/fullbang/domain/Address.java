package day6.fullbang.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int id;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private Place place;

    private String siDo;
    private String siGunGu;
    private String eupMyeonDong;
    private String addressCode;
    private String addressLocality;
    private String streetAddress;
    private String latitude;
    private String longitude;

}
