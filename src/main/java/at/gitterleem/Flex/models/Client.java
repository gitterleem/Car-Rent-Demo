package at.gitterleem.Flex.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter	@Setter
	private Long id;

	@Getter @Setter
	private String title;

	@Getter @Setter
	private String suffix;

	@NotBlank
	@Getter @Setter
	private String firstName;

	@NotBlank
	@Getter @Setter
	private String lastName;

	@NotNull
	@Getter @Setter
	private LocalDate dateOfBirth;

	@NotBlank
	@Getter @Setter
	private String email;

	@ManyToOne
	@Getter @Setter
	private Address address;

	@ManyToOne
	@Getter  @Setter
	private Address billingAddress;

}
