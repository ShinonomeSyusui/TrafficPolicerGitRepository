package katachi.spring.trafficPolicer.domain.trafficPolicer.model;

import lombok.Data;

@Data
public class ViolationFines {

	private int id;
	private int violationId;
	private int vehicleTypeNameId;
	private String violationName;
	private int fines;
}
