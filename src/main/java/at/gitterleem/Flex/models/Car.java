package at.gitterleem.Flex.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;

	@NotNull
	@ManyToOne
	@Getter @Setter
	private Manufacturer manufacturer;

	@NotNull
	@Column
	@Getter @Setter
	private String model;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Getter @Setter
	private Transmission transmission;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Getter @Setter
	private Fuel fuel;

	@NotNull
	@Getter @Setter
	private int seatCount;

	@NotNull
	@Getter @Setter
	private int doorCount;
}
