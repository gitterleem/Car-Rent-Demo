package at.gitterleem.Flex.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter	@Setter
	private Long id;

	@NotBlank
	@Getter @Setter
	private String street;

	@NotBlank
	@Getter @Setter
	private String streetNumber;

	@NotBlank
	@Getter @Setter
	private String zip;

	@NotBlank
	@Getter @Setter
	private String city;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Getter @Setter
	private Country country;

}
