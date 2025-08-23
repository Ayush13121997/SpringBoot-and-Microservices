package in.SpringLearning.service;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.SpringLearning.entity.Role;
import in.SpringLearning.entity.User;
import in.SpringLearning.repo.UserRepo;

@Service
public class UserRoleService {
	
	@Autowired
	private UserRepo userRepo ;

	public void saveUserWithRoles()
	{
		Role r1 = new Role();
		r1.setRoleName("Manager");
		
		Role r2 = new Role();
		r2.setRoleName("Admin");
		
		User user= new User();
		user.setUserName("kartik");
		user.setUserDob(LocalDate.now().minusYears(26));
		user.setGender("M");
		user.setRoles(Arrays.asList(r1,r2));
		
		User user2 = new User();
		user2.setUserName("Shivam");
		user2.setUserDob(LocalDate.now().minusYears(25));
		user2.setGender("M");
		user2.setRoles(Arrays.asList(r2));
		
		userRepo.saveAll(Arrays.asList(user,user2));
		
		
		
	}
	
	
}
