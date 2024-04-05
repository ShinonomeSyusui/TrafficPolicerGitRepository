package katachi.spring.trafficPolicer.domain.trafficPolicer.model;

import lombok.Data;

@Data
public class Officer {

	private int id;
	private String controlNumber;
	private String password;
	private String affiliation;
	private String grade;
	private String familyName;
	private String firstName;
}
