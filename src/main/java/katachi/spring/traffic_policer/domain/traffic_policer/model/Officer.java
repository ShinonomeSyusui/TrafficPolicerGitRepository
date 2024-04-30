package katachi.spring.traffic_policer.domain.traffic_policer.model;

import lombok.Data;
/**
 * Officerのモデルクラス、DBの項目とフィールドが対応している。
 * @author ShinonomeSyusui
 * @version 1.0.0
 */
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
