package in.SpringLearning.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgRestController {

	@GetMapping(value = "/welcome" , produces = "text/plain")
	public String getWelcomeMsg()
	{
		
//		 int num = 10/0 ;

		
		return "Welcome to the Rest API .....";
	}
	
	@GetMapping(value ="/greet" , produces = "text/plain")
	public  ResponseEntity<String> getGreetMsg()
	{
		String msg = "Good Morning ....." ;
	
		
		return new ResponseEntity<>(msg ,HttpStatus.OK);
	}
}