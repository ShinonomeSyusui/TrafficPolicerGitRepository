package katachi.spring.trafficPolicer.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * このクラスは、過去の違反歴検索フォームで入力された値を、エンティティクラスに渡すためのクラス
 * @author ShinonomeSyusui
 * @version 1.0.0
 */
@Data
public class ViolationRecordSearchForm {

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDay;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDay;
	
	private String violationLocation;
	
	private Integer violation;
}
