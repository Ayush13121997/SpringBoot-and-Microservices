package in.SpringLearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.SpringLearning.Entity.Product;
import in.SpringLearning.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/saveProduct")
	public ResponseEntity<String> saveProduct(@RequestBody Product product){
		
		Boolean savedStatus = productService.saveProduct(product);
		
		if(savedStatus) {
			
			String msg = "Product Saved Successfully";
			
			return new ResponseEntity<>(msg ,HttpStatus.CREATED);
		}
		else {
			
			String msg = "Please provide correct Data";
			
			return new ResponseEntity<>(msg ,HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		}
		
	}
	
	
	@GetMapping("/product/{pid}")
	public ResponseEntity<Product> getProduct(@PathVariable long pid){
		
		Product product = productService.getProduct(pid);
		
		return new ResponseEntity<Product>(product , HttpStatus.OK);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		
		List<Product> productList = productService.getAllProducts();
		
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}
	
	@PutMapping("/updateProduct/{pid}")
	public ResponseEntity<String> updateProduct(@PathVariable long pid , @RequestBody Product product){
		
		Boolean updatedProduct = productService.updateProduct(pid , product);
		
		if(updatedProduct) {
			
			String msg = "Product have been successfully updated";
			
			return new ResponseEntity<>(msg , HttpStatus.OK);
		}
		else {
			
			String msg = "please provide the correct data";
			
			return new ResponseEntity<>(msg , HttpStatus.BAD_REQUEST);
			
		}

	}
	
	@DeleteMapping("/deleteProduct/{pid}")
	public ResponseEntity<String> deleteProduct(@PathVariable long pid){
		
		Boolean deleteStatus = productService.deleteProduct(pid);
		if(deleteStatus) {
			String msg = "Product have been successfully deleted";
			
			return new ResponseEntity<>(msg , HttpStatus.OK);
		}
		else {
			String msg = "Product have been successfully deleted";
			
			return new ResponseEntity<>(msg , HttpStatus.OK);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
