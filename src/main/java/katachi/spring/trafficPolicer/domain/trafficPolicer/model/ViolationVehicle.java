package katachi.spring.trafficPolicer.domain.trafficPolicer.model;

import lombok.Data;
/**
 * ViolationVehicleのモデルクラス、DBの項目とフィールドが対応している。
 * @author ShinonomeSyusui
 * @version 1.0.0
 */
@Data
public class ViolationVehicle {
	
	private int id;
	
	private int vehicleTypeNameId;
	
	private String violationVehicleType;

}
