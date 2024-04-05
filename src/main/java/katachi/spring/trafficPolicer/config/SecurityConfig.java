package katachi.spring.trafficPolicer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig  {
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		http.authorizeHttpRequests(
				authorize->authorize.requestMatchers("/webjars/**", "/css/**").permitAll()
					.anyRequest().authenticated())
			.formLogin(
				login->login.loginPage("/loginPage")
					.loginProcessingUrl("/loginPage")
					.usernameParameter("controlNumber")
					.passwordParameter("password")
					.defaultSuccessUrl("/homePage", true).failureUrl("/loginPage?error").permitAll());
		http.logout(logout -> logout
		        	.logoutUrl("/logout")
		        	.logoutSuccessUrl("/loginPage")
		        );
		return http.build();
	}
}