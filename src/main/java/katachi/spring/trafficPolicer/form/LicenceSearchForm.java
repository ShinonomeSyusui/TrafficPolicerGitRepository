package katachi.spring.trafficPolicer.form;

import java.math.BigInteger;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LicenceSearchForm {
	
	@NotNull
	private BigInteger licenceNumber;
}
