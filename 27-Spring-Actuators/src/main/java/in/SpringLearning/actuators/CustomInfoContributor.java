package in.SpringLearning.actuators;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class CustomInfoContributor implements InfoContributor {

	@Override
	public void contribute(Builder builder) {
		builder.withDetail("AppName", "Spring Boot Actuator Example")
			   .withDetail("Version", "1.0.0")
			   .withDetail("Description", "This is a sample application to demonstrate Spring Boot Actuators.");
	}

}
