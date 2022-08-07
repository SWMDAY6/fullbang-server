package day6.fullbang.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import day6.fullbang.domain.Place;
import day6.fullbang.dto.request.CoordinateRangeDto;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PlaceRepository {

    private final EntityManager em;

    public List<Place> findAll() {
        String query = "select p from Place p";
        return em.createQuery(query, Place.class).getResultList();
    }

    public List<Place> findPlacesByCoordinate(CoordinateRangeDto coordinateRangeDto) {
        double latitudeStart = coordinateRangeDto.getLatitudeStart();
        double longitudeStart = coordinateRangeDto.getLongitudeStart();

        double latitudeEnd = coordinateRangeDto.getLatitudeEnd();
        double longitudeEnd = coordinateRangeDto.getLongitudeEnd();
        String query = "SELECT p FROM Place p WHERE (p.address.latitude BETWEEN :latitudeStart AND :latitudeEnd)"
                + "AND (p.address.longitude BETWEEN :longitudeStart AND :longitudeEnd)";
        return em.createQuery(query, Place.class)
                .setParameter("latitudeStart", latitudeStart)
                .setParameter("latitudeEnd", latitudeEnd)
                .setParameter("longitudeStart", longitudeStart)
                .setParameter("longitudeEnd", longitudeEnd)
                .getResultList();
    }

    public List<Place> findPlacesByPlaceName(String placeName) {
        String query = "SELECT p FROM Place p WHERE p.name LIKE :placeNameQuery";

        return em.createQuery(query, Place.class)
                .setParameter("placeNameQuery", "%" + placeName + "%")
                .getResultList();
    }
}
