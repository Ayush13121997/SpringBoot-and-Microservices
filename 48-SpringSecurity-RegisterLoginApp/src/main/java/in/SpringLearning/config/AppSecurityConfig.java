package in.SpringLearning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import in.SpringLearning.repo.CustomerService;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
	
	
	@Autowired
	CustomerService customerService;
	
	@Bean
	PasswordEncoder encoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationProvider authProvider() {
		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(customerService);
		
		daoAuthenticationProvider.setPasswordEncoder(encoder());
		
		return daoAuthenticationProvider;
	}
	
	@Bean
	AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception {
		
		return authConfig.getAuthenticationManager();
		
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests((requests)-> {
			requests.requestMatchers("/register" ,"/login").permitAll()
			.anyRequest()
			.authenticated();
		})
		.csrf(AbstractHttpConfigurer::disable)
		.sessionManagement((session) -> {
			session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		})
		.headers((headers) -> {
			headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin);
		});
		
		return  http.build();
		
	}
	

}
