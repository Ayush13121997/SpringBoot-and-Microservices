package in.SpringLearning.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.SpringLearning.exception.UserNotFoundException;

@RestController
public class UserController {
	
	@GetMapping("/user/{id}")
	public ResponseEntity<String> getUserById(@PathVariable int id) {
		if(id <= 0) {
			throw new UserNotFoundException("User ID must be greater than zero");
		}
		
		// Dummy user data for demonstration
		String user = "User" + id;
		
		return new ResponseEntity<String>(user, HttpStatus.OK);
	}
	
	@GetMapping("/user/create")
	public ResponseEntity<String> createUser(String name) {
		if(name == null || name.isEmpty()) {
			throw new IllegalArgumentException("User name cannot be null or empty");
		}
		
		// Dummy user creation logic
		String newUser = "Created User: " + name;
		
		return new ResponseEntity<String>(newUser, HttpStatus.CREATED);
	}

}
