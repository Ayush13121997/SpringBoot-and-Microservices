package in.SpringLearning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgController {
	
	@GetMapping("/greet")
	public String greetMsg() {
		
		String msg = "Good Morning";
		
		System.out.println(msg);
		
		return msg;
	}

}
