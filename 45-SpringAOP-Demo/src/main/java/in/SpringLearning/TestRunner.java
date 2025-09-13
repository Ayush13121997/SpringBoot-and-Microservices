package in.SpringLearning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.SpringLearning.dao.EmployeeDao;

@Component
public class TestRunner implements CommandLineRunner{

	@Autowired
	private EmployeeDao dao;
	
	@Override
	public void run(String... args) throws Exception {
		
		dao.saveEmployee();
		
		System.out.println("***********************");
		
		dao.updateEmployee();
		
		System.out.println("***********************");
		
		dao.deleteEmployee();
		
	}

}
