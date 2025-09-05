package in.SpringLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import in.SpringLearning.model.Customer;
import in.SpringLearning.util.KafkaConstants;

@Service
public class CustomerService {

	@Autowired
	private KafkaTemplate<String, Customer> kafkaTemplate;
	
	public String add(List<Customer> customers) {

		if (!customers.isEmpty()) {
			for (Customer customer : customers) {
				
				kafkaTemplate.send(KafkaConstants.TOPIC, customer);
				
				System.out.println("*********************************Message Published to Kafka Topic****************************");
				
			}
		}

		return "Customers record added to Kafka Queue Successfully";
	}

}
