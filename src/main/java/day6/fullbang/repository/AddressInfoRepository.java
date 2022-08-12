package day6.fullbang.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import day6.fullbang.domain.AddressInfo;
import day6.fullbang.dto.addressInfo.AddressInfoDto;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AddressInfoRepository {

    private final EntityManager em;

    public List<AddressInfoDto> getAddressInfoByCoordinateRange(Double latitudeStart, Double latitudeEnd,
        Double longitudeStart, Double longitudeEnd, int regionDepth) {

        List<AddressInfoDto> result = new ArrayList<>();

        List<AddressInfo> addressInfos = em.createQuery(
            "SELECT a FROM AddressInfo a "
                + "WHERE (a.latitude BETWEEN :latitudeStart AND :latitudeEnd) "
                + "AND (a.longitude BETWEEN :longitudeStart AND :longitudeEnd)", AddressInfo.class)
            .setParameter("latitudeStart", latitudeStart)
            .setParameter("latitudeEnd", latitudeEnd)
            .setParameter("longitudeStart", longitudeStart)
            .setParameter("longitudeEnd", longitudeEnd).getResultList();

        addressInfos.forEach(addressInfo -> {
            try {
                result.add(new AddressInfoDto(addressInfo, regionDepth));
            } catch (NullPointerException e) {
                return;
            }
        });

        return result;
    }
}
