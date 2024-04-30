package katachi.spring.traffic_policer.domain.traffic_policer.model;

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
