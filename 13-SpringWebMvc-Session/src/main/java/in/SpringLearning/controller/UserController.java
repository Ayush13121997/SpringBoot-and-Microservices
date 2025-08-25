package in.SpringLearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.SpringLearning.DTO.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@GetMapping("/")
	public String index(Model model) {
		
		UserDTO UserDTO = new UserDTO();
		
		model.addAttribute("user", UserDTO);
		
		return "index";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("user") UserDTO user, Model model, HttpServletRequest request) {
		
		if(user.getEmail().equals("admin@gmail.com") && user.getPwd().equals("admin@123")) {
			
			HttpSession session = request.getSession(true);
			
			session.setAttribute("email", user.getEmail());
			
			session.setMaxInactiveInterval(100);
			
			return "redirect:dashboard";
		}
		else {
			
			model.addAttribute("msg", "Invalid Credentials");
			
			return "index";
		}
	}
	
	
	@GetMapping("/dashboard")
	public String dashboard(HttpServletRequest request, Model model) {
		
		
		HttpSession session = request.getSession(false);
		
		String email = (String) session.getAttribute("email");
		
		model.addAttribute("email", email);
		
		return "dashboard";
	}
	
	@GetMapping("/edu-details")
	public String eduDetails(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession(false);
		
		String email = (String) session.getAttribute("email");
		
		model.addAttribute("email", email);
		
		return "edu-details";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		
		session.invalidate();
		
		return "redirect:/";
	}
}
