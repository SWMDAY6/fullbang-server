package day6.fullbang.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import day6.fullbang.domain.Image;
import day6.fullbang.domain.Room;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RoomRepository {

    private final EntityManager em;

    public List<Room> findRoomsByPlaceId(Long placeId) {
        String query = "SELECT DISTINCT r FROM Room r "
            + "JOIN FETCH r.products pr "
            + "JOIN FETCH r.place p "
            + "WHERE p.id = :placeId ";

        return em.createQuery(query, Room.class)
            .setParameter("placeId", placeId)
            .getResultList();
    }

    public List<Image> findImageByRoomId(Long roomId) {
        String query = "SELECT i FROM Image i "
            + "JOIN FETCH i.room r "
            + "WHERE r.id = :roomId";

        return em.createQuery(query, Image.class)
            .setParameter("roomId", roomId)
            .getResultList();
    }

}
