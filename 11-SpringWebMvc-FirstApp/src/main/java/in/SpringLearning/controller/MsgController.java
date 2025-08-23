package in.SpringLearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/msg")
public class MsgController {

	@GetMapping("/greet")
	public ModelAndView greetMsg()
	{
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("msg","Good Morning....");
		
		mav.setViewName("index");
		
		return mav;
	}
	
	@GetMapping("/welcome")
	public String welcomeMsg(Model model)
	{
		model.addAttribute("msg", "Welcome to the Coding World");
		return "index";
	}
	
	@GetMapping("/login")
	@ResponseBody
	public String loginMsg()
	{
		String msg = "Login Successfull .......";
		
		return msg;
	}
}
