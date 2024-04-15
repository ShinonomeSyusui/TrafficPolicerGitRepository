package katachi.spring.trafficPolicer.domain.trafficPolicer.model;

import lombok.Data;

@Data
public class DriversSubInfo {

	private int id;
	
	private int violationRecordId;
	
	private int jobId;
	
	private String subAddress;
	
	private String tel;
	
	private String subName;
	
	private Integer subAge;
	
	private String relationship;
	
	private int subJobId;
	
	private Job job;
	
}
