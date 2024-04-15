package katachi.spring.trafficPolicer.domain.trafficPolicer.model;

import java.math.BigInteger;
import java.util.Date;

import lombok.Data;

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
	
	
	
	private LicenceType licenceType;
	
	private DriversSubInfo driversSubInfo;
}
