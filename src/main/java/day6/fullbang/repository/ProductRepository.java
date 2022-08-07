package day6.fullbang.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import day6.fullbang.domain.Product;
import day6.fullbang.dto.product.PriceInfoDto;
import day6.fullbang.dto.request.MarketPriceCondition;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;

    public List<PriceInfoDto> getPriceInfoByAddressCode(MarketPriceCondition marketPriceCondition,
        String addressCodeHead) {

        LocalDateTime checkInDateTime = LocalDate.parse(marketPriceCondition.getDate(), DateTimeFormatter.ISO_DATE)
            .atStartOfDay();
        LocalDateTime checkOutDateTime = checkInDateTime.plusDays(2);

        List<Product> products = em.createQuery(
            "SELECT p FROM Product p "
                + "WHERE p.room.place.address.addressCode LIKE :addressCodePattern "
                + "AND p.room.place.type = :placeType "
                + "AND p.room.maximumCapacity >= :capacity "
                + "AND p.checkInDateTime >= :checkInDateTime "
                + "AND p.checkOutDateTime < :checkOutDateTime ", Product.class) //TODO add parking_avail condition
            .setParameter("addressCodePattern", addressCodeHead + "%")
            .setParameter("placeType", marketPriceCondition.getPlaceType())
            .setParameter("capacity", marketPriceCondition.getCapacity())
            .setParameter("checkInDateTime", checkInDateTime)
            .setParameter("checkOutDateTime", checkOutDateTime)
            .getResultList();

        List<PriceInfoDto> priceInfos = new ArrayList<>();

        products.forEach(product -> priceInfos.add(new PriceInfoDto(product.getPrice())));

        return priceInfos;
    }

}
