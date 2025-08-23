package in.SpringLearning.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.SpringLearning.productdao.ProductRepo;
import in.SpringLearning.productentity.Product;

@Service
public class ProductService {
     
	@Autowired
	private ProductRepo productRepo ;
	
	public void saveProduct()
	{
		Product p = new Product();
		p.setPid(1);
		
		p.setPname("Laptop");
		
		p.setPrice(20000.00);
		
		productRepo.save(p);
		
	}
	
}
