package in.SpringLearning.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgRestController {
	
	@GetMapping("/welcome")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String welcomeMsg() {
		
		return "Welcome to the Spring Security Learning";
	}
	
	@GetMapping("/user")
	public String userMsg() {
		
		return "Hello User!!.." ;
	}
	
	@GetMapping("/admin")
	public String adminMsg() {
		
		return "Hello Admin!!" ;
	}

}
