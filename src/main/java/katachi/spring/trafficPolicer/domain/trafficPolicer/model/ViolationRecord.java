package katachi.spring.trafficPolicer.domain.trafficPolicer.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
/**
 * ViolationRecordのモデルクラス、DBの項目とフィールドが対応している。
 * @author ShinonomeSyusui
 * @version 1.0.0
 */
@Data
public class ViolationRecord {

	private int id;
	
	private int officerId;
	
	private int licenceId;
	
	private int jobId;
	
	private int violationVehicleId;
	
	private int age;
	
	private boolean heavyTowingVehicle;
	
	private Integer vehicle;
	
	private String vehicleRegistrationNumber;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime dateAndTimeOfViolation;
	
	private String violationLocation;
	
	private int violationAndPointFinesId;
	
	private String speed;
	
	private String resultOverSpeed;
	
	private String legalSpeed;
	
	private String overSpeed;
	
	private String detailes;
	
	private String supplementaryColumn;
	
	private String carelessness;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date appearanceDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date publicationTime;
	
	//各モデルクラスのフィールド値を参照する為のクラスフィールド
	private Officer officer;
	private Licence licence;
	private Job job;
	private ViolationVehicle violationVehicle;
	private ViolationAndPointFines violationAndPointFines;
	private DriversSubInfo driversSubInfo;
	
	private int violationRecordId;
	
	/**
	 * dateAndTimeOfViolationの値を和暦に変換して返すメソッド
	 * @return 和暦に変換された日付け
	 */
	public String getWarekiHenkan() {
		String result = null;
		   Locale locale = new Locale("ja","JP","JP");
		   DateFormat warekiFormat = new SimpleDateFormat("GGGGyy年MM月dd日HH時mm分頃", locale);
		   if(Objects.nonNull(dateAndTimeOfViolation)) {
		       Date convertedDate = Date.from(dateAndTimeOfViolation.atZone(ZoneId.systemDefault()).toInstant());
		       result = warekiFormat.format(convertedDate);
		   }
		   return result;
	}
	
	/**
	 * heavyTowingVehicleの値がtrueならば、文字列"重被牽引車"を返すメソッド
	 * @return
	 */
	public String getHeavyTowingVehicleSelect() {
		if(heavyTowingVehicle) {
			return "重被牽引車";
		}
		return null;
		
	}
}
