package in.SpringLearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import in.SpringLearning.security.PwdSecurity;

@Configuration
@ComponentScan(basePackages = "in.SpringLearning")
public class AppConfig {

	public AppConfig() {

		System.out.println("AppConfig :: Constructor");
	}
	
	@Bean
	public PwdSecurity createBean()
	{
		PwdSecurity security  = new PwdSecurity();
		
		return security;
	}

}
