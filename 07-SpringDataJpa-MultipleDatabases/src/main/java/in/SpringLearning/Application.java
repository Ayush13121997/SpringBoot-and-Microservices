package in.SpringLearning;
import in.SpringLearning.productservice.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import in.SpringLearning.orderservice.OrderService;

@SpringBootApplication
public class Application{

   
   

	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		
		System.out.println("---------------Data Insertion Started ------------------------");
		
		OrderService orderService = context.getBean(OrderService.class);
		
		orderService.saveOrder();
		
		ProductService productService = context.getBean(ProductService.class);
		
		productService.saveProduct();
		
		
		System.out.println("---------------Data Saved Successfully------------------------");
	}

	

}
