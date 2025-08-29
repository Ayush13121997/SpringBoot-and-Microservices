package in.SpringLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import in.SpringLearning.dto.Quote;
import in.SpringLearning.dto.QuotesResponseDTO;
import reactor.core.publisher.Mono;

@Service
public class QuoteService {
	
	@Value("${quotes.random.api.url}")
	private String randomQuoteApiUrl ;
	
	@Value("${quotes.api.url}")
	private String apiUrl ;
	
	
	public void getRandomQuote() {
		
		WebClient webClient = WebClient.create();
		
		Mono<Quote> bodyToMono = webClient.get()
				.uri(randomQuoteApiUrl)
				.retrieve()
				.bodyToMono(Quote.class);
		
		// making synchronus call using WebClient (blocking thread)
		Quote quote = bodyToMono.block();
		
		System.out.println(quote);
	}
	
	public void getQuoteAsync() {
		
		WebClient webClient = WebClient.create();
		
		Mono<QuotesResponseDTO> bodyToMono = webClient.get()
				.uri(apiUrl,200)
				.retrieve()
				.bodyToMono(QuotesResponseDTO.class);
		
		// making asynchronus call using WebClient (non-blocking thread)
		bodyToMono.subscribe(quoteResponse -> {
			List<Quote> quotes = quoteResponse.getQuotes();
			for(Quote quote : quotes) {
			handleResponse(quote);
			}
		});
		
		System.out.println("Request Sent");
		
		System.out.println("Doing some other work in main thread");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Exiting main thread");
	}
	
	public void handleResponse(Quote response) {
		
		System.out.println(response);
	}
	
}