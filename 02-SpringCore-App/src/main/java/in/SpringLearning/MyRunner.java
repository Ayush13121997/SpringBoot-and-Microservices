package in.SpringLearning;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyRunner {

	public static void main(String[] args) {
		
	  // start an IOC container by giving xml file as input
      ApplicationContext cntxt = new  ClassPathXmlApplicationContext("spring-beans.xml");
      
      System.out.println("========== IOC Started ==========");

	  // getting bean obj from IOC
      Car car = cntxt.getBean(Car.class);
      
      car.drive();
      
      

	}

}
