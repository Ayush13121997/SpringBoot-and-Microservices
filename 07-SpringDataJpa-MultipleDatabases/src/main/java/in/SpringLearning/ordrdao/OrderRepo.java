package in.SpringLearning.ordrdao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.SpringLearning.ordrentity.Order;

@Repository
public interface OrderRepo extends CrudRepository<Order,Integer>{

}
