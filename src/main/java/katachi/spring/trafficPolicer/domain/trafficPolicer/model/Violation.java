package katachi.spring.trafficPolicer.domain.trafficPolicer.model;

import lombok.Data;

@Data
public class Violation {

	private int id;
	private int amountOfViolationId;
	private String generalViolation;
	private int violationPoints;
	private AmountOfViolation amountOfViolation;
}
