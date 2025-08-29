package in.SpringLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.SpringLearning.entity.User;
import in.SpringLearning.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo ;
	
	@Override
	public Boolean saveUser(User user) {
		
		User savedUser = userRepo.save(user);
		
		if(savedUser != null) {
			return true ;
		}
		else {
			return false;
		}
	}

	@Override
	public List<User> getUsers() {
		
		return userRepo.findAll();
	}
	
	

}
