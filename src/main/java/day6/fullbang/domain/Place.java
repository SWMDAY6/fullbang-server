package day6.fullbang.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "place")
@NoArgsConstructor
@Getter
public class Place {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "place_id")
	private Long id;

	@Column(name = "place_name")
	private String name;

	@Column(name = "place_type")
	@Enumerated(EnumType.STRING)
	private PlaceType type;

	@Embedded
	private Address address;

	private String contactInfo;

	@Column(columnDefinition = "LONGTEXT")
	private String detailInfo;

	private Boolean parkingAvailability;

	@Column(unique = true)
	private Long yanoljaId;

	@Column(unique = true)
	private Long yeogieottaeId;

	@OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
	private final List<Room> rooms = new ArrayList<>();
}
