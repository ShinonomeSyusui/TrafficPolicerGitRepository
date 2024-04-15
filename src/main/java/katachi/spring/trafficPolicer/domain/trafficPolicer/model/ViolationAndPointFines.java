package katachi.spring.trafficPolicer.domain.trafficPolicer.model;

import lombok.Data;

@Data
public class ViolationAndPointFines {

	private int id;
	
	private int vehicleTypeNameId;
	
	private String violationName;
	
	private int violationPoints;
	
	private int fines;
}
