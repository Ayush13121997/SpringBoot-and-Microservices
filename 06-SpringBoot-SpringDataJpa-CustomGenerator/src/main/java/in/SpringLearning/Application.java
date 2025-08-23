package in.SpringLearning;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import in.SpringLearning.entity.OrderDetails;
import in.SpringLearning.service.OrderDetailsService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		
		
		OrderDetailsService orderDetailsService = context.getBean(OrderDetailsService.class);
		
		OrderDetails order = new OrderDetails();
		
		order.setOrderBy("Shivam");
		
		order.setOrderPlacesDate(new Date());
		
		orderDetailsService.saveOrder(order);
	}

}
