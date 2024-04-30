package katachi.spring.traffic_policer.domain.traffic_policer.model;

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
