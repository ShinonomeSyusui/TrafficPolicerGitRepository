package katachi.spring.trafficPolicer.domain.trafficPolicer.model;

import lombok.Data;
/**
 * ViolationAndPointFinesのモデルクラス、DBの項目とフィールドが対応している。
 * @author ShinonomeSyusui
 * @version 1.0.0
 */
@Data
public class ViolationAndPointFines {

	private int id;
	
	private int vehicleTypeNameId;
	
	private String violationName;
	
	private int violationPoints;
	
	private int fines;
}
