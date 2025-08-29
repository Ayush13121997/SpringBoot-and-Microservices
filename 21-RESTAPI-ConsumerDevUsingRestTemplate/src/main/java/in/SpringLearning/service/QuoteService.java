package in.SpringLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.SpringLearning.dto.Quote;
import in.SpringLearning.dto.QuotesResponseDTO;

@Service
public class QuoteService {
	
	@Value("${quotes.random.api.url}")
	private String randomQuoteApiUrl ;
	
	@Value("${quotes.api.url}")
	private String apiUrl ;
	
	public void m1() {
		
		RestTemplate rt = new RestTemplate();
		
		ResponseEntity<String> forEntity = rt.getForEntity(randomQuoteApiUrl, String.class);
		
		int statusCode = forEntity.getStatusCode().value();
		
		if(statusCode != 200) {
			throw new RuntimeException("Quote API is down");
		}
		else {
			System.out.println("Quote API is up and running");
			
			String response = forEntity.getBody();
			System.out.println(response);
		}	
		
	}
	
	public void m2() {
		
		RestTemplate rt = new RestTemplate();
		
		ResponseEntity<Quote> forEntity = rt.getForEntity(randomQuoteApiUrl, Quote.class);
		
		int statusCode = forEntity.getStatusCode().value();
		
		if(statusCode != 200) {
			throw new RuntimeException("Quote API is down");
		}
		else {
			System.out.println("Quote API is up and running");
			
			Quote response = forEntity.getBody();
			System.out.println(response);
		}
		
	}
	
	public void m3() {
		
		RestTemplate rt = new RestTemplate();
		
		
		
		ResponseEntity<QuotesResponseDTO> forEntity = rt.getForEntity(apiUrl, QuotesResponseDTO.class);
		
		int statusCode = forEntity.getStatusCode().value();
		
		if(statusCode != 200) {
			throw new RuntimeException("Quote API is down");
		}
		else {
			System.out.println("Quote API is up and running");
			
			QuotesResponseDTO body = forEntity.getBody();
			
			List<Quote> quotes = body.getQuotes();
			
			quotes.forEach(System.out::println);
			
		}
		
	}
	
}