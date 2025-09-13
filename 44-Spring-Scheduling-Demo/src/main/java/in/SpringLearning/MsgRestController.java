package in.SpringLearning;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class MsgRestController {
	

	@Scheduled(fixedRate = 1500)
	public void welcomeMsg() {
		
		System.out.println("Welcome to Spring Scheduling" + LocalDateTime.now());
	}

	@Scheduled(cron = "20 08 23 * * *")
	public void greetMsg() {
		System.out.println("Good Morning : " + LocalDateTime.now());
	}
}
