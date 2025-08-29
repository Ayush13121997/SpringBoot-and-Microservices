package in.SpringLearning.service;

import java.util.List;

import in.SpringLearning.Entity.Product;

public interface ProductService {

	Boolean saveProduct(Product product);

	Product getProduct(long pid);

	List<Product> getAllProducts();

	Boolean updateProduct(long pid, Product product);

	Boolean deleteProduct(long pid);
	
	

}
