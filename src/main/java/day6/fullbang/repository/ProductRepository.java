package day6.fullbang.repository;

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
}
