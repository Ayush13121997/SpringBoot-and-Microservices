package in.SpringLearning.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class MsgConsumerController {
	
	@Value("${spring.security.url}")
	private  String url ;
	
	@Value("${spring.security.username}")
	private  String username ;
	
	@Value("${spring.security.password}")
	private  String pwd ;
	
	
	
	@GetMapping("/consume/msg/rt")
	public ResponseEntity<String>  consumeMsg() {
		
		String cred = username + ":" + pwd ;
		
		byte[] encodeCred = Base64.getEncoder().encode(cred.getBytes());
		
		String headerKey = "Authorization";
				
		String headerValue = "Basic " + new String(encodeCred);
		
		RestTemplate template = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.set(headerKey,headerValue);
		
		HttpEntity<Object> entity = new HttpEntity<>(headers);
		
		ResponseEntity<String> responseEntity = template.exchange(url, HttpMethod.GET, entity, String.class);
		
		String body = responseEntity.getBody();
		
		System.out.println(body);
		
		return responseEntity;
	}
	
	@GetMapping("/consume/msg/Webclient")
	public ResponseEntity<String> cosumeMsgUsingWebClient(){
		
		String cred = username + ":" + pwd ;
		
		byte[] encodeCred = Base64.getEncoder().encode(cred.getBytes());
		
		WebClient webClient = WebClient.create();
		
		String block = webClient.get()
				.uri(url)
				.header("Authorization", "Basic " + new String(encodeCred))
				.header("key", "val")
				.retrieve()
				.bodyToMono(String.class)
				.block();
		
		System.out.println(block);
		
		return new ResponseEntity<String>(block ,HttpStatus.OK);
	}

}
