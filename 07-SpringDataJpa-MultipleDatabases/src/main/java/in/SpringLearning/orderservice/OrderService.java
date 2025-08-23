package in.SpringLearning.orderservice;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.SpringLearning.ordrdao.OrderRepo;
import in.SpringLearning.ordrentity.Order;

@Service
public class OrderService {

	@Autowired
	private OrderRepo orderRepo ;
	
	
	public void saveOrder()
	{
		Order o = new Order();
		
		o.setOrderId(1);
		o.setOrderDate(LocalDate.now());
		o.setOrderBy("Ayush");
		
		orderRepo.save(o);
		
	}
}
