package katachi.spring.traffic_policer.form;

import java.math.BigInteger;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author ShinonomeSyusui
 * @version 1.0.0
 */
@Data
public class LicenceSearchForm {
	
	@NotNull
	private BigInteger licenceNumber;
}
