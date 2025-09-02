package in.SpringLearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.SpringLearning.feign.WelcomeApiClient;

@RestController
public class GreetRestController {
	
	@Autowired
	private WelcomeApiClient welcomeApiClient;
	
	@GetMapping("/greet")
	public String greet() {
		
		String welcomeApiResponse = welcomeApiClient.invokeWelcomeApi();
		
		String greetApiResponse = "Good Morning!!!.....";
		
		return welcomeApiResponse + " " + greetApiResponse;
	}

}
