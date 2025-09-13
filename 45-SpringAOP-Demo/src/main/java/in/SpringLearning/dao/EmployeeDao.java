package in.SpringLearning.dao;

import org.springframework.stereotype.Component;

import in.SpringLearning.annotation.MyTx;

@Component
public class EmployeeDao {
	
	public void saveEmployee() {
		
		System.out.println("Employee saved !!...");
		
//		if(new Random().nextInt(10)<5) {
//			
//			throw new RuntimeException("Some DB issue");
//		}
	}
	
	public String updateEmployee() {
		
		return "Employee Updated !!...";
	}
	
	@MyTx
	public String deleteEmployee() {
		
		return "Employee Deleted !!...";
	}

}
