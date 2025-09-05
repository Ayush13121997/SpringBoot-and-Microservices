package in.SpringLearning.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import in.SpringLearning.model.Customer;
import in.SpringLearning.util.KafkaConstants;

@Service("customerService")
public class CustomerService {
	
	@KafkaListener(topics = KafkaConstants.TOPIC , groupId = KafkaConstants.GROUP_ID)
	public Customer listener(@Payload Customer customer)
	{
		System.out.println("****Message received from Kafka Topic :: " +  customer);
		
		return customer;
	}
}
