package katachi.spring.traffic_policer.form;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * フォーム画面で受け取った値をエンティティクラスに渡す為のクラスです。
 * @author ShinonomeSyusui
 * @version 1.0.0
 */
@Data
public class TrafficViolationNoticeCreationForm {
	
	private int officerId;
	
	private int licenceId;
	
	@NotBlank
	private String furigana;
	
	@NotBlank
	private String violatorName;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	
	private String age;
	
	@NotBlank
	private String address;
	
	private int jobId;
	
	@NotNull
	private Integer gender;
	
	@NotNull
	private BigInteger licenceNumber;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date issueDate;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expiryDate;
	
	@NotBlank
	private String prefectures;
	
	private String subAddress;
	
	//@Pattern(regexp = "^[0-9]{2,4}-[0-9]{2,4}-[0-9]{4}$|^$")
	private String tel;
	
	private String subName;
	
	private Integer subAge;
	
	private int subJobId;
	
	private String relationship;
	
	@NotNull
	private Integer violationVehicleId;
	
	private String largeTypeSelect;
	
	private String standardSelect;
	
	private String motorcycleSelect;
	
	private String scootersSelect;
	
	private boolean heavyTowingVehicle;
	
	@NotNull
	private Integer vehicle;
	
	@NotBlank
	private String vehicleRegistrationNumber;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime DateAndTimeOfViolation;
	
	@NotBlank
	private String violationLocation;
	
	private int violation;
	
	private String speed;
	
	private String resultOverSpeed;
	
	private String legalSpeed;
	
	private String overSpeed;
	
	private String detailes;
	
	private String SupplementaryColumn;
	
	private String carelessness;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date appearanceDate;
	
	/**
	 * heavyTowingVehicleのフィールド値がtrueであれば、"重被牽引車"の文字列を返すメソッド
	 * @return
	 */
	public String getHeavyTowingVehicleSelect() {
		if(heavyTowingVehicle) {
			return "重被牽引車";
		}
		return null;
	}
}

