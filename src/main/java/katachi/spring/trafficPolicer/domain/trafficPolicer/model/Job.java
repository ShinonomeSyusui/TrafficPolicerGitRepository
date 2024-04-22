package katachi.spring.trafficPolicer.domain.trafficPolicer.model;

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
