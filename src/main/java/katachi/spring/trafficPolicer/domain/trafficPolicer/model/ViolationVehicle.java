package katachi.spring.trafficPolicer.domain.trafficPolicer.model;

import lombok.Data;

@Data
public class ViolationVehicle {
	
	private int id;
	private int vehicleTypeNameId;
	private String violationVehicleType;

}
