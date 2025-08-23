package in.SpringLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.SpringLearning.entity.OrderDetails;
import in.SpringLearning.repo.OrderDetailsRepository;

@Service
public class OrderDetailsService {
	
	private OrderDetailsRepository orderDetailsRepository ;

	@Autowired
	public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
		this.orderDetailsRepository = orderDetailsRepository;
	}
	
	public void saveOrder(OrderDetails order)
	{
		OrderDetails orderDetails = orderDetailsRepository.save(order);
		
		System.out.println(orderDetails);
		
		System.out.println("Orders Details Inserted Successfully");
	}

}
