package in.SpringLearning.ordrdao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.SpringLearning.ordrentity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer>{

	
	

}
