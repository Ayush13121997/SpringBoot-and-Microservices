package in.SpringLearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.SpringLearning.entity.Customer;
import in.SpringLearning.repo.CustomerRepo;

@RestController
public class CustomerRestController {
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public ResponseEntity<String> saveCustomer(@RequestBody Customer customer){
		
		if(customer != null) {
			
			String encodedPwd = passwordEncoder.encode(customer.getPwd());
			
			customer.setPwd(encodedPwd);
			
			customerRepo.save(customer);
			
			
			return new ResponseEntity<>("Customer Registered Succesfully",HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>("Invalid Customer Data",HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginCustomer(@RequestBody Customer customer){
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customer.getEmail(),customer.getPwd());
		
		try {
			
			Authentication authenticate = authenticationManager.authenticate(authenticationToken);
			
			if(authenticate != null) {
				return new ResponseEntity<String>("Login Succesful" , HttpStatus.OK);
			}
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("Login Failed" , HttpStatus.BAD_REQUEST);
	}

}
