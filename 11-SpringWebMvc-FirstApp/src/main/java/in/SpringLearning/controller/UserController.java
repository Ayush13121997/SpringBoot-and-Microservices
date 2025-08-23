package in.SpringLearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/User")
public class UserController {

	//Url => http://localhost:8080/greet?name=Ayush
	@GetMapping("/greet")
	@ResponseBody
	public String greetMsg(@RequestParam("name")  String name) {

		String msg = name + " Good Evening...!!!";

		return msg;
	}
	
	@GetMapping("/welcome/{name}")
	@ResponseBody
	public String welcomeMsg(@PathVariable("name") String name)
	{
		String msg = name + " Welcome to the Page";
		
		return msg ;
	}
}
