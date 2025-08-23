package in.SpringLearning;

import in.SpringLearning.service.EmplService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		
		EmplService emplService = context.getBean(EmplService.class);
		
		emplService.saveEmpWithAddr();
	}

}
