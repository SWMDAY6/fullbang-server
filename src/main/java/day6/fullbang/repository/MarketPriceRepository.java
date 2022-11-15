package day6.fullbang.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Repository;

import day6.fullbang.domain.MarketPrice;
import day6.fullbang.dto.response.MarketPriceDto;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MarketPriceRepository {
    private final EntityManager em;

    public MarketPrice getMarketPriceByAddressCode(String addressCodeHead) {
        List<MarketPrice> marketPrices;
        try {
            marketPrices = em.createQuery("SELECT m FROM MarketPrice m WHERE m.addressCodeHead = :addressCodeHead" ,
                MarketPrice.class)
                .setParameter("addressCodeHead", addressCodeHead)
                .getResultList();
        } catch(NullPointerException e) {
            return null;
        }

        if (marketPrices.size() == 0) {
            return null;
        }

        return marketPrices.get(0);
    }

    public void save(MarketPriceDto marketPriceDto) {
        MarketPrice marketPrice = new MarketPrice();
        if (Double.isNaN(marketPriceDto.getMean())) {
            return;
        }
        marketPrice.setMean(marketPriceDto.getMean());
        marketPrice.setMaxMeanOfRange(marketPriceDto.getMaxMeanOfRange());
        marketPrice.setMinMeanOfRange(marketPriceDto.getMinMeanOfRange());
        marketPrice.setPlaceType(marketPriceDto.getPlaceType());
        marketPrice.setAddressCodeHead(marketPriceDto.getAddressInfoDto().getAddressCodeHead());
        System.out.println(marketPrice.getId());
        em.persist(marketPrice);
    }
}
