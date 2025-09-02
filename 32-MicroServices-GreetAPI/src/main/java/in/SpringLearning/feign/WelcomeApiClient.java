package in.SpringLearning.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="31-Microservices-WelcomeAPI")
public interface WelcomeApiClient {
	
	@GetMapping("/welcome")
	public String invokeWelcomeApi();

}
