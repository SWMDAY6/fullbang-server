package day6.fullbang.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import day6.fullbang.domain.Product;
import day6.fullbang.dto.product.PriceInfoDto;
import day6.fullbang.dto.service.MarketPriceCondition;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;

    public List<PriceInfoDto> getPriceInfoByAddressCode(MarketPriceCondition marketPriceCondition) {

        LocalDateTime checkInDateTime = LocalDate.parse(marketPriceCondition.getDate(), DateTimeFormatter.ISO_DATE)
            .atStartOfDay();
        LocalDateTime checkOutDateTime = checkInDateTime.plusDays(2);

        return em.createQuery(
            "SELECT product FROM Product product INNER JOIN Room room ON product.room = room "
                + "INNER JOIN Place place ON room.place = place "
                + "WHERE place.address.addressCode LIKE :addressCodePattern "
                + "AND place.type = :placeType "
                + "AND room.maximumCapacity >= :capacity "
                + "AND product.checkInDateTime >= :checkInDateTime "
                + "AND product.checkOutDateTime < :checkOutDateTime ", PriceInfoDto.class) //TODO add parking_avail condition
            .setParameter("addressCodePattern", marketPriceCondition.getAddressCodeHead() + "%")
            .setParameter("placeType", marketPriceCondition.getPlaceType())
            .setParameter("capacity", marketPriceCondition.getCapacity())
            .setParameter("checkInDateTime", checkInDateTime)
            .setParameter("checkOutDateTime", checkOutDateTime)
            .getResultList();
    }

}
