package in.SpringLearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.SpringLearning.entity.User;
import in.SpringLearning.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/saveUser" , consumes = {"application/json" ,"application/xml"})
	public ResponseEntity<String> saveUser(@RequestBody User user) {

		Boolean IsUSerSaved = userService.saveUser(user);

		if (IsUSerSaved) {

			String msg = "User Created Successfully";

			return new ResponseEntity<String>(msg, HttpStatus.CREATED);
			
		} else {

			String msg = "Please provide Valid data";

			return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		}

	}
	
	@GetMapping(value ="/getUsers" , produces = {"application/json" ,"application/xml"})
	public ResponseEntity<List<User>> getUsers(){
		
		List<User> users = userService.getUsers();
		
		if(users != null) {
			
			return new ResponseEntity<List<User>>(users,HttpStatus.OK);
		
		}
		return null;
	
	}
}
