package katachi.spring.traffic_policer.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import katachi.spring.traffic_policer.domain.traffic_policer.model.Licence;

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
