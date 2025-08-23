	package in.SpringLearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.SpringLearning.entity.User;
import in.SpringLearning.service.UserService;
import jakarta.validation.Valid;

@Controller
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String loadUserForm(Model model) {

		User userObject = new User();
		model.addAttribute("user", userObject);
		return "index"; 
	}
	
	@GetMapping("/users")
	public String loadUserList(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "users"; 
	}
	
	@GetMapping("/loadUserById")
	public String loadUserById(@RequestParam("uid")   Integer uid, Model model) {
		User user = userService.getUserById(uid);
		if (user != null) {
			model.addAttribute("user", user);
			return "index"; 
		} else {
			model.addAttribute("emsg", "User not found with ID: " + uid);
			return "error"; 
		}
	}
	
	@GetMapping("/deleteUserById")
	public String deleteUserById(@RequestParam("uid")  Integer uid, Model model) {
		boolean isDeleted = userService.deleteUserById(uid);
		if (isDeleted) {
			model.addAttribute("smsg", "User deleted successfully with ID: " + uid);
		} else {
			model.addAttribute("emsg", "User not found with ID: " + uid);
		}
		return "redirect:/users"; 
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@Valid  User user, BindingResult binding , Model model) {
		
		
		if (binding.hasErrors()) {
			model.addAttribute("emsg", "Validation errors occurred. Please correct them.");
			return "index"; 
		}	
		boolean isSaved = userService.saveUser(user);
		if (isSaved) {
			model.addAttribute("smsg", "User saved successfully!");
		} else {
			model.addAttribute("emsg", "Failed to save user.");
		}
		return "index"; 
	}

}