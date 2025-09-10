package in.SpringLearning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class DataRestController {
	
	
	@GetMapping("/data")
	@CircuitBreaker(fallbackMethod = "getDataFromDB" , name ="ayush")
	public String getDataFromRedis() {
		
		System.out.println("*******Redis Method Called**************");
		
		int i = 10/0 ;
		
		return "Reterived Data from Redis";
	}
	
	public String getDataFromDB(Throwable t ) {
		
		System.out.println("******Database Method Called************");
		
		return "Reterived Data From Database" ;
	}

}
