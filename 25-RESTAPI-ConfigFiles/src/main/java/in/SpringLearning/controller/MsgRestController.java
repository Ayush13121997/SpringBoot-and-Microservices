package in.SpringLearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.SpringLearning.config.AppProperties;

@RestController
public class MsgRestController {
	
	@Value("${app.messages.greet}")
	private String greetMsg;
	
	@Autowired
	private AppProperties appProperties;
	
	@GetMapping(value = "/greet" , produces = "text/plain" )
	public String getGreetMsg(@RequestParam(value = "name", defaultValue = "Guest") String name) {
		
		String msg = name + ", " + greetMsg;
		return msg;
	}
	
	@GetMapping(value = "/welcome/{name}"  , produces = "text/plain" )
	public ResponseEntity<String> getWelcomeMsg(@PathVariable("name") String name) {
		
		String msg = name + ", " + appProperties.getMessages().get("welcome");
		return new ResponseEntity<>(msg , HttpStatus.OK);
	}

}
