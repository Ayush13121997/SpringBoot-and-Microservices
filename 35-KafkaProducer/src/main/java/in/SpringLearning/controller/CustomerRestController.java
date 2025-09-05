package in.SpringLearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.SpringLearning.model.Customer;
import in.SpringLearning.service.CustomerService;

@RestController
public class CustomerRestController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customer")
	public String addCustomer(@RequestBody List<Customer> customers) {
		
		return customerService.add(customers);
	}

}
