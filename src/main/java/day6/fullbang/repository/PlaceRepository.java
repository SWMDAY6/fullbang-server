package day6.fullbang.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import day6.fullbang.domain.Place;
import day6.fullbang.dto.CoordinateRangeDto;
import day6.fullbang.dto.request.FilterOptionRequestDto;
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

    public List<Place> findPlacesByOption(FilterOptionRequestDto filterOptionRequestDto) {

        String query = "SELECT DISTINCT p FROM Place p "
                + "WHERE p IN (SELECT r FROM Room r JOIN r.products pr "
                + "WHERE pr.checkInDateTime = :checkInDate ";

        if (filterOptionRequestDto.getPlaceType() != null) {
            query += "AND p.type = :placeType ";
        }
        if (filterOptionRequestDto.getParkingAvailability() != null) {
            query += "AND p.parkingAvailability = :parkingAvailability ";
        }
        if (filterOptionRequestDto.getMaximumCapacity() != null) {
            query += "AND r.maximumCapacity >= :maximumCapacity ";
        }
        query += ")";

        LocalDate date = filterOptionRequestDto.getCheckInDateTime();

        return em.createQuery(query, Place.class)
                .setParameter("placeType", filterOptionRequestDto.getPlaceType())
                .setParameter("parkingAvailability", filterOptionRequestDto.getParkingAvailability())
                .setParameter("maximumCapacity", filterOptionRequestDto.getMaximumCapacity())
                .setParameter("checkInDate", date.atStartOfDay())
                .getResultList();
    }
}
