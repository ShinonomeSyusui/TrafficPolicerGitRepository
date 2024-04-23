package katachi.spring.trafficPolicer.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import katachi.spring.trafficPolicer.domain.trafficPolicer.model.Licence;

/**
 * @author ShinonomeSyusui
 * @version 1.0.0
 */
@Configuration
public class JavaConfig {
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	Licence licence() {
		return new Licence();
	}
}
