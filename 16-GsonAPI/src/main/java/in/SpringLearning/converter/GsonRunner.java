package in.SpringLearning.converter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

import in.SpringLearning.dto.Product;

public class GsonRunner {

	public static void main(String[] args) {

		GsonRunner runner = new GsonRunner();

		runner.JsonToJavaObject();
		runner.JavaObjectToJson();
	}

	private void JavaObjectToJson() {

		Product product = new Product();
		product.setPid(101);
		product.setPname("Laptop");
		product.setPrice(45000.00);

		System.out.println(product);

		Gson gson = new Gson();
		String json = gson.toJson(product);

		try {
			Files.write(Paths.get("product.json"), json.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Serialization Successful.....");

	}

	private void JsonToJavaObject() {

		FileReader fr = null;
		try {
			fr = new FileReader("product.json");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Gson gson = new Gson();
		Product product = gson.fromJson(fr, Product.class);
		System.out.println(product);
		System.out.println("De-Serialization Successful.....");

	}

}
