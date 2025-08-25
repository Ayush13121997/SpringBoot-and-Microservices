package in.SpringLearning.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.SpringLearning.dto.User;
import in.SpringLearning.service.EmailService;
import jakarta.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private EmailService emailService;
	
	@GetMapping("/")
	public String loadUserForm(Model model) {

		User userObject = new User();
		model.addAttribute("user", userObject);
		return "index"; 
	}
	
	@PostMapping("/user-submit")
	public String saveUser(@Valid @ModelAttribute("user") User user , Model model , BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "index";
		}
		
		String Subject = "Spring Email Service";
		
		String body = "Welcome to Spring Email Service";	
		
		boolean isSent = emailService.sendEmail(user.getEmail(), Subject, body);
		
		if(isSent) {
			model.addAttribute("smsg", "User Form Submitted and Email Sent Successfully!");
		}
		else {
			model.addAttribute("smsg", "Failed to Send Email!");
		}
		
		
		return "index"; 
	}

}