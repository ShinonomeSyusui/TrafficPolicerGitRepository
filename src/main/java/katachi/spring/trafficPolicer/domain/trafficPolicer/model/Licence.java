package katachi.spring.trafficPolicer.domain.trafficPolicer.model;

import java.math.BigInteger;
import java.util.Date;

import lombok.Data;
/**
 * Licenceのモデルクラス、DBのtableとフィールドが対応している。
 * @author ShinonomeSyusui
 * @version 1.0.0
 */
@Data
public class Licence {

	private int id;
	
	private BigInteger licenceNumber;
	
	private Date expiryDate;
	
	private String driverName;
	
	private String furigana;
	
	private String address;
	
	private Date birthday;
	
	private Date issueDate;
	
	private Integer gender;
	
	private String prefectures;
	
	
	//LicenceTypeのクラスとDriversSubInfoのクラスのフィールド値も扱える
	private LicenceType licenceType;
	
	private DriversSubInfo driversSubInfo;
}
