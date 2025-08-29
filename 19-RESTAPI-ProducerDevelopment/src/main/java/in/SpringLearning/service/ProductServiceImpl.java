package in.SpringLearning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.SpringLearning.Entity.Product;
import in.SpringLearning.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepo productRepo ;

	@Override
	public Boolean saveProduct(Product product) {
		
		Product savedProduct = productRepo.save(product);
		
		if(savedProduct != null) {
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public Product getProduct(long pid) {
		
		Optional<Product> byId = productRepo.findById(pid);

		if(byId.isPresent()) {
			
			return byId.get();
			
		}
		
		return null;
	}

	@Override
	public List<Product> getAllProducts() {
		
		return productRepo.findAll();
	}

	@Override
	public Boolean updateProduct(long pid, Product product) {
		
		Optional<Product> byId = productRepo.findById(pid);
		if(byId.isPresent()) {
			
			 Product oldProduct = byId.get();
			 
			 oldProduct.setPname(product.getPname());
			 oldProduct.setPrice(product.getPrice());
			 
			 Product updatedProduct = productRepo.save(oldProduct);
			 
			 if(updatedProduct != null) {
				 return true;
			 }	
		}
		
		return false;
	}

	@Override
	public Boolean deleteProduct(long pid) {
		
		if(productRepo.existsById(pid)) {
			
			productRepo.deleteById(pid);
			
			return true;
			
		}
		
		return false;
	}
	
	

}
