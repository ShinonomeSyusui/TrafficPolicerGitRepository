package katachi.spring.traffic_policer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("katachi.spring.traffic_policer")
public class TrafficPolicerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrafficPolicerApplication.class, args);
	}

}
