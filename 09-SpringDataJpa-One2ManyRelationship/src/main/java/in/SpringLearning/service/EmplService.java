package in.SpringLearning.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.SpringLearning.entity.Address;
import in.SpringLearning.entity.Employee;
import in.SpringLearning.repo.EmpRepo;

@Service
public class EmplService {

	private EmpRepo empRepo ;
	
	
	@Autowired
	public EmplService(EmpRepo empRepo) {
		this.empRepo = empRepo;
	}



	public void saveEmpWithAddr()
	{
		Employee emp = new Employee();
	
		emp.setEmpName("Ayush");
		
		emp.setSalary(35000.00);
		
		List<Address> addressList = new ArrayList<>();
		
		Address addr1 = new Address();
		
		addr1.setCity("Noida");
		
		addr1.setState("UP");
		
		addr1.setType("LOCAL");
		
		addr1.setEmployee(emp);
		
		
		
        Address addr2 = new Address();
		
        addr2.setCity("Gurugram");
		
        addr2.setState("Haryana");
		
        addr2.setType("Permanent");
        
        addr2.setEmployee(emp);
		
		
	    emp.setAddress(Arrays.asList(addr1,addr2));
	    
	    empRepo.save(emp);
		
	
	}
}
