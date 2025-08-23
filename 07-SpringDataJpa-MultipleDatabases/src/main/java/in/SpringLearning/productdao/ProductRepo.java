package in.SpringLearning.productdao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.SpringLearning.productentity.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product,Integer>{
	
	

}
