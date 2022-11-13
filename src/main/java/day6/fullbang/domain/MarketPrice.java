package day6.fullbang.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import day6.fullbang.dto.addressInfo.AddressInfoDto;
import day6.fullbang.dto.response.MarketPriceDto;
import day6.fullbang.service.AddressInfoService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "market_price")
@NoArgsConstructor
@Getter
@Setter
public class MarketPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "market_price_id")
    private Long id;

    @Column(name = "place_type")
    @Enumerated(EnumType.STRING)
    private PlaceType placeType;

    private String addressCodeHead;
    private Double mean;
    private Double minMeanOfRange;
    private Double maxMeanOfRange;
}
