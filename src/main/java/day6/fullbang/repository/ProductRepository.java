package day6.fullbang.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import day6.fullbang.domain.Place;
import day6.fullbang.domain.Product;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;

    public Product findOne(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> searchProductByPlace(Place place) {
        return em.createQuery("select p from Product p where p.room.place = :place", Product.class)
            .setParameter("place", place)
            .getResultList();
    }

    public List<Product> findByPlaceName(String placeName, LocalDate date) {

        return em.createQuery(
            "select p from Product p where p.room.place.name = :placeName and p.checkInDateTime >= :checkInDate and p.checkOutDateTime < :checkOutDate",
            Product.class)
            .setParameter("placeName", placeName)
            .setParameter("checkInDate", date.atStartOfDay())
            .setParameter("checkOutDate", date.plusDays(2).atStartOfDay())
            .getResultList();
    }
}
