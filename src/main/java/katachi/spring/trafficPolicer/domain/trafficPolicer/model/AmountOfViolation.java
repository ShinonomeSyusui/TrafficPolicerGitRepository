package katachi.spring.trafficPolicer.domain.trafficPolicer.model;

import lombok.Data;

@Data
public class AmountOfViolation {

	private int id;
	private String violation;
	private int largeSizeFine;
	private int standardSizeFine;
	private int motorcycleFine;
	private int smallSpecialVehicleFine;
	private int scooters;
}
