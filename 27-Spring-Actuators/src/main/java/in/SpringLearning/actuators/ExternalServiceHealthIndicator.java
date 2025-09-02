package in.SpringLearning.actuators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("externalService")
public class ExternalServiceHealthIndicator implements HealthIndicator {

	@Autowired
	private ExternalServiceChecker externalServiceChecker;

	@Override
	public Health health() {
	    if (externalServiceChecker.isServiceUp()) {
	        return Health.up()
	            .withDetail("External Service", "Available")
	            .build();
	    } else {
	        return Health.down()
	            .withDetail("External Service", "Not Available")
	            .build();
	    }
	}

}
