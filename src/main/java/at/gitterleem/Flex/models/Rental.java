package at.gitterleem.Flex.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Rental {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter	@Setter
	private Long id;

	@ManyToOne
	@Getter @Setter
	private Car car;

	@ManyToOne
	@Getter @Setter
	private Client client;

	@Getter  @Setter
	private LocalDateTime startTime;

	@Getter @Setter
	private LocalDateTime endTime;

	@Getter @Setter
	private BigDecimal startKilometer;

	@Getter @Setter
	private BigDecimal endKilometer;

	@Getter @Setter
	private BigDecimal ratePerDay;

	@Getter @Setter
	private BigDecimal includedKilometer = BigDecimal.ZERO;

	@Getter @Setter
	private BigDecimal ratePerKilometer;

	@ManyToOne
	@Getter @Setter
	private Address startAddress;

	@ManyToOne
	@Getter @Setter
	private Address endAddress;

	@Override
	public String toString() {
		return "Rental{" +
				"id=" + id +
				", car=" + car +
				", client=" + client +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", startKilometer=" + startKilometer +
				", endKilometer=" + endKilometer +
				", ratePerDay=" + ratePerDay +
				", includedKilometer=" + includedKilometer +
				", ratePerKilometer=" + ratePerKilometer +
				", startAddress=" + startAddress +
				", endAddress=" + endAddress +
				'}';
	}
}

