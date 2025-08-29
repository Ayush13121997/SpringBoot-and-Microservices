package in.SprinLearning.converter;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.SprinLearning.DTO.Customer;

public class JacksonRunner{

	public static void main(String[] args) {
			
		JacksonRunner jackson = new JacksonRunner();
		
		jackson.convertJavaToJson();
		
		jackson.convertJsonToJava();
	}

	private void convertJsonToJava() {
		
		File file = new File("customer.json");
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Customer customer = mapper.readValue(file, Customer.class);
			System.out.println(customer);
			System.out.println("JSON file to Java Object conversion done!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void convertJavaToJson() {
		
		Customer customer =new Customer();
		
		customer.setId(101);
		customer.setName("John Doe");
		customer.setEmail("johndoe@gmail.com");
		
		System.out.println(customer);
		
		File file = new File("customer.json");
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			mapper.writeValue(file, customer);
			System.out.println("Java Object to JSON file conversion done!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
