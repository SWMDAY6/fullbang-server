package day6.fullbang.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import day6.fullbang.domain.Place;
import day6.fullbang.dto.request.CoordinateRangeByDateDto;
import day6.fullbang.dto.request.FilterOptionRequestDto;
import day6.fullbang.dto.request.SearchRequestDto;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PlaceRepository {

    private final EntityManager em;

    public List<Place> findPlacesByCoordinate(CoordinateRangeByDateDto coordinateRangeByDateDto) {
        String query = "SELECT p FROM Place p "
            + "WHERE (p.address.latitude BETWEEN :latitudeStart AND :latitudeEnd) "
            + "AND (p.address.longitude BETWEEN :longitudeStart AND :longitudeEnd) "
            + "AND p IN (SELECT r FROM Room r JOIN r.products pr "
            + "WHERE pr.checkInDateTime = :checkInDate)";

        return em.createQuery(query, Place.class)
            .setParameter("latitudeStart", coordinateRangeByDateDto.getLatitudeStart())
            .setParameter("latitudeEnd", coordinateRangeByDateDto.getLatitudeEnd())
            .setParameter("longitudeStart", coordinateRangeByDateDto.getLongitudeStart())
            .setParameter("longitudeEnd", coordinateRangeByDateDto.getLongitudeEnd())
            .setParameter("checkInDate", coordinateRangeByDateDto.getCheckInDate().atStartOfDay())
            .getResultList();
    }

    public List<Place> findPlacesByOption(FilterOptionRequestDto filterOptionRequestDto) {
        double latitudeStart = filterOptionRequestDto.getLatitudeStart();
        double longitudeStart = filterOptionRequestDto.getLongitudeStart();

        double latitudeEnd = filterOptionRequestDto.getLatitudeEnd();
        double longitudeEnd = filterOptionRequestDto.getLongitudeEnd();

        String query = "SELECT DISTINCT p FROM Place p "
            + "WHERE (p.address.latitude BETWEEN :latitudeStart AND :latitudeEnd) "
            + "AND (p.address.longitude BETWEEN :longitudeStart AND :longitudeEnd) "
            + "AND p IN (SELECT r FROM Room r JOIN r.products pr "
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

        return em.createQuery(query, Place.class)
            .setParameter("latitudeStart", latitudeStart)
            .setParameter("latitudeEnd", latitudeEnd)
            .setParameter("longitudeStart", longitudeStart)
            .setParameter("longitudeEnd", longitudeEnd)
            .setParameter("checkInDate", filterOptionRequestDto.getCheckInDate().atStartOfDay())
            .setParameter("placeType", filterOptionRequestDto.getPlaceType())
            .setParameter("parkingAvailability", filterOptionRequestDto.getParkingAvailability())
            .setParameter("maximumCapacity", filterOptionRequestDto.getMaximumCapacity())
            .getResultList();
    }

    public List<Place> findPlacesByPlaceName(SearchRequestDto searchRequestDto) {

        String nativequery = "SELECT * "
            + "FROM place p "
            + "LEFT JOIN room r ON p.place_id = r.place_id "
            + "LEFT JOIN product pr ON r.room_id = pr.product_id "
            + "WHERE p.place_name LIKE :keyword "
            + "AND pr.check_in_date_time = :checkInDate "
            + "ORDER BY(6371*acos(cos(radians(p.latitude))*cos(radians(:latitude))*cos(radians(:longitude) "
            + "-radians(p.longitude))+sin(radians(p.latitude))*sin(radians(:latitude))))";

        List<Place> listResults = em.createNativeQuery(nativequery, Place.class)
            .setParameter("longitude", searchRequestDto.getLongitude())
            .setParameter("latitude", searchRequestDto.getLatitude())
            .setParameter("keyword", "%" + searchRequestDto.getKeyword() + "%")
            .setParameter("checkInDate", searchRequestDto.getCheckInDate().atStartOfDay())
            .getResultList();

        return listResults;
    }
}

