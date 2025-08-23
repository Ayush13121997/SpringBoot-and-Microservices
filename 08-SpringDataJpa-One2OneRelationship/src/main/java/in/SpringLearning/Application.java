package in.SpringLearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import in.SpringLearning.service.PersonPassportService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		
		PersonPassportService personPassportService = context.getBean(PersonPassportService.class);
		
		personPassportService.savePersonWithPassport();
		
	}

}
