package katachi.spring.trafficPolicer.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

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
