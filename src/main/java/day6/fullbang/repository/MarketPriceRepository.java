package day6.fullbang.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Repository;

import day6.fullbang.domain.MarketPrice;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MarketPriceRepository {

    private EntityManager em;

    public MarketPrice getMarketPriceByAddressCode(String addressCodeHead) {
        List<MarketPrice> marketPrices;
        marketPrices = em.createQuery("SELECT m FROM MarketPrice m WHERE m.addressCodeHead = :addressCodeHead" ,
            MarketPrice.class)
            .setParameter("addressCodeHead", addressCodeHead)
            .getResultList();

        if (marketPrices.size() == 0) {
            return null;
        }

        return marketPrices.get(0);
    }
}
