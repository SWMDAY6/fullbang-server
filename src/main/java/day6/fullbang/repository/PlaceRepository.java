package day6.fullbang.repository;

import java.time.LocalDate;
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
            + "WHERE pr.checkInDateTime BETWEEN :checkInDate and :checkOutDate)";

        return em.createQuery(query, Place.class)
            .setParameter("latitudeStart", coordinateRangeByDateDto.getLatitudeStart())
            .setParameter("latitudeEnd", coordinateRangeByDateDto.getLatitudeEnd())
            .setParameter("longitudeStart", coordinateRangeByDateDto.getLongitudeStart())
            .setParameter("longitudeEnd", coordinateRangeByDateDto.getLongitudeEnd())
            .setParameter("checkInDate", coordinateRangeByDateDto.getCheckInDate().atStartOfDay())
            .setParameter("checkOutDate", coordinateRangeByDateDto.getCheckInDate().plusDays(1).atStartOfDay())
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
            + "WHERE pr.checkInDateTime BETWEEN :checkInDate and :checkOutDate ";

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

        LocalDate date = filterOptionRequestDto.getCheckInDate();

        return em.createQuery(query, Place.class)
            .setParameter("latitudeStart", latitudeStart)
            .setParameter("latitudeEnd", latitudeEnd)
            .setParameter("longitudeStart", longitudeStart)
            .setParameter("longitudeEnd", longitudeEnd)
            .setParameter("checkInDate", date.atStartOfDay())
            .setParameter("checkOutDate", date.plusDays(1).atStartOfDay())
            .setParameter("placeType", filterOptionRequestDto.getPlaceType())
            .setParameter("parkingAvailability", filterOptionRequestDto.getParkingAvailability())
            .setParameter("maximumCapacity", filterOptionRequestDto.getMaximumCapacity())

            .getResultList();
    }

    public List<Place> findPlacesByPlaceName(SearchRequestDto searchRequestDto) {
        String query = "SELECT p FROM Place p WHERE p.name LIKE :placeNameQuery"
            + " AND p IN (SELECT r FROM Room r JOIN r.products pr "
            + "WHERE pr.checkInDateTime BETWEEN :checkInDate and :checkOutDate)";

        // Double distance = ACOS[sin(lat1) * sin(lat2) + cos(lat1) * cos(lat2) * cos(lng1 - lng2)];

        return em.createQuery(query, Place.class)
            .setParameter("placeNameQuery", "%" + searchRequestDto.getKeyword() + "%")
            .setParameter("checkInDate", searchRequestDto.getCheckInDate().atStartOfDay())
            .setParameter("checkOutDate", searchRequestDto.getCheckInDate().plusDays(1).atStartOfDay())
            .getResultList();
    }

    // private static double distance(double lat1, double lat2, double long1, double long2) {
    //     double theta = long1 - long2;
    //
    //     double dist =
    //         Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
    //             * Math.cos(deg2rad(theta));
    //     dist = Math.acos(dist);
    //     dist = rad2deg(dist);
    //
    //     dist = dist * 60 * 1.1515 * 1609.344;
    // }
}
