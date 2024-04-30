package katachi.spring.traffic_policer.domain.traffic_policer.model;

import lombok.Data;
/**
 * Jobのモデルクラス、DBのTableの項目とフィールドに対応している。
 * @author ShinonomeSyusui
 * @version 1.0.0
 */
@Data
public class Job {

	private int id;
	
	private String job;
}
