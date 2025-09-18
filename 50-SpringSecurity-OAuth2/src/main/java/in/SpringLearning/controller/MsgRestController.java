package in.SpringLearning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgRestController {
	
	@GetMapping("/")
	public String welcomeMsg() {
		
		String msg = "Welcome to Spring Oauth Learning";
		
		return msg;
	}

}
