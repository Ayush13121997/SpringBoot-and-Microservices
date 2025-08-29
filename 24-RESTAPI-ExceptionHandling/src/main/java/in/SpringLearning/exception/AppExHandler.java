package in.SpringLearning.exception;

import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExHandler {
	
	@ExceptionHandler(exception=UserNotFoundException.class , produces = "application/json")
	public ResponseEntity<ExInfo> handleUserNotFoundEx(UserNotFoundException ex) {
		
		ExInfo exInfo = new ExInfo();
		exInfo.setExCode("404");
		exInfo.setExMsg(ex.getMessage());
		exInfo.setExDate(LocalDateTime.now());
		
		return new ResponseEntity<ExInfo>(exInfo, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(exception=Exception.class , produces = "application/json")
	public ResponseEntity<ExInfo> handleAllEx(Exception ex) {
		
		ExInfo exInfo = new ExInfo();
		exInfo.setExCode("500");
		exInfo.setExMsg(ex.getMessage());
		exInfo.setExDate(LocalDateTime.now());
		
		return new ResponseEntity<ExInfo>(exInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(exception=SQLException.class , produces = "application/json")
	public ResponseEntity<ExInfo> handleSQLEx(Exception ex) {
		
		ExInfo exInfo = new ExInfo();
		exInfo.setExCode("501");
		exInfo.setExMsg(ex.getMessage());
		exInfo.setExDate(LocalDateTime.now());
		
		return new ResponseEntity<ExInfo>(exInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
