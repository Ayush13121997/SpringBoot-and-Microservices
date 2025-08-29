package in.SpringLearning.entity;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookRestController {
	
	@GetMapping(value = "/book" , produces = "application/json")
	public ResponseEntity<Book> getBook()
	{
		Book book = new Book(101,"Java",450.00) ;
		
		return new ResponseEntity<>(book , HttpStatus.OK) ;
	}
	
	@GetMapping(value = "/books" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Book>> getBooks()
	{
		Book book = new Book(102,"Spring",550.00) ;
		
		Book book1 = new Book(103,"Hibernate",650.00) ;
		
		Book book2 = new Book(104,"Microservices",750.00) ;
		
		List<Book> bookList = List.of(book,book1,book2) ;
		
		return new ResponseEntity<>(bookList , HttpStatus.OK) ;
	}

}
