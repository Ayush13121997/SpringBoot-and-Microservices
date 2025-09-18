package in.SpringLearning.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import in.SpringLearning.jwt.AuthEntryPointJwt;
import in.SpringLearning.jwt.AuthTokenFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	AuthEntryPointJwt unauthorizedHandler;

    @Bean
    AuthTokenFilter authTokenFilter() {
		return new AuthTokenFilter();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests((authorizeRequests) -> {
			authorizeRequests.requestMatchers("/signin").permitAll()
			.anyRequest()
			.authenticated();
		})
		.sessionManagement((session) -> {
			session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		})
		.exceptionHandling((exception) -> {
			exception.authenticationEntryPoint(unauthorizedHandler);
		})
		.headers((headers) -> {
			headers.frameOptions((frameoptions) -> {
				frameoptions.sameOrigin();
			});
		})
		.csrf((csrf)-> {
			csrf.disable();
		})
		.addFilterBefore(authTokenFilter(),
                UsernamePasswordAuthenticationFilter.class);
		
	    

	    return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	UserDetailsService userDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}
	
	@Bean
	CommandLineRunner initData(UserDetailsService userDetailsService) {
		
		return args -> {
			
			JdbcUserDetailsManager manager = (JdbcUserDetailsManager) userDetailsService;
			
			UserDetails user1 = User.withUsername("user1")
					.password(passwordEncoder().encode("user@111"))
					.roles("USER")
					.build();
			
			UserDetails user2 = User.withUsername("admin")
					.password(passwordEncoder().encode("admin@123"))
					.roles("ADMIN")
					.build();
			
			JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
			userDetailsManager.createUser(user1);
			userDetailsManager.createUser(user2);
			
		};
	}	
}
