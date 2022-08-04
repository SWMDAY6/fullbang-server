package day6.fullbang.dto.response;

import day6.fullbang.domain.Address;
import day6.fullbang.domain.Place;
import day6.fullbang.domain.PlaceType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlaceResponseDto {
	private long id;
	private String name;
	private PlaceType type;

	private String addressFullName;
	private String region1DepthName;
	private String region2DepthName;
	private String region3DepthName;
	private String addressCode;
	private double latitude;
	private double longitude;

	public PlaceResponseDto(Place place) {
		this.id = place.getId();
		this.name = place.getName();
		this.type = place.getType();

		Address address = place.getAddress();
		this.addressFullName = address.getAddressFullName();
		this.region1DepthName = address.getRegion1DepthName();
		this.region2DepthName = address.getRegion2DepthName();
		this.region3DepthName = address.getRegion3DepthName();
		this.addressCode = address.getAddressCode();
		this.latitude = address.getLatitude();
		this.longitude = address.getLongitude();
	}

}