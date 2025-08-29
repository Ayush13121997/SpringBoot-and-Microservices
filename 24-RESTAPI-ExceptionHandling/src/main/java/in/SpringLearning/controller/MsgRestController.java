package in.SpringLearning.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.SpringLearning.exception.ExInfo;

@RestController
public class MsgRestController {
	
	@GetMapping("/welcome")
	public ResponseEntity<String> getWelcomeMsg() {
		
		String msg = "Welcome to Spring Boot REST API Exception Handling";
		
		int x = 10/0;
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@ExceptionHandler(exception=ArithmeticException.class , produces = "application/json")
	public ResponseEntity<ExInfo> handleEx(ArithmeticException ex) {
		
		ExInfo exInfo = new ExInfo();
		exInfo.setExCode("400");
		exInfo.setExMsg(ex.getMessage());
		exInfo.setExDate(LocalDateTime.now());
		
		return new ResponseEntity<ExInfo>(exInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
