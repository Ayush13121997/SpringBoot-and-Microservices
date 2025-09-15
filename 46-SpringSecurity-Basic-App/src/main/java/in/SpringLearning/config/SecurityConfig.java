package in.SpringLearning.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled =true)
public class SecurityConfig {

	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests((requests) -> {
			
			requests.requestMatchers("/admin").permitAll()
					.anyRequest().authenticated();		
		})
		.httpBasic(Customizer.withDefaults())
		.formLogin(Customizer.withDefaults())
		.sessionManagement((session) -> {
			session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		})
		.headers((headers) -> {
			headers.frameOptions(HeadersConfigurer.FrameOptionsConfig :: sameOrigin);
			
		})
		.csrf(AbstractHttpConfigurer::disable);
		
		
		return http.build();
	}
	
	
	@Bean
	InMemoryUserDetailsManager inMemoryUsers(PasswordEncoder passwordEncoder) {

		UserDetails u1 = User.builder()
							 .username("ayush")
							 .password(passwordEncoder.encode("ayush123@"))
							 .roles("USER")
							 .build();

		UserDetails u2 = User.builder()
							 .username("raju")
							 .password(passwordEncoder.encode("raju@991"))
							 .roles("ADMIN")
							 .build();

		UserDetails u3 = User.builder()
							 .username("shivam")
							 .password(passwordEncoder.encode("shivam@1312"))
							 .roles("SUPERADMIN")
							 .build();

		return new InMemoryUserDetailsManager(Arrays.asList(u1, u2, u3));

	}

	@Bean
	PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

}
