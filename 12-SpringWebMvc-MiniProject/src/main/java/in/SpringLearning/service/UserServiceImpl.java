package in.SpringLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.SpringLearning.entity.User;
import in.SpringLearning.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	
	private UserRepo userRepo;

	@Autowired
	public UserServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	
	public boolean saveUser(User user) {
		
		
		User saveUser = userRepo.save(user);
		
		return saveUser.getUid() != null;
	}
	
	
	public User getUserById(Integer uid) {
		
		return userRepo.findById(uid).orElse(null);
	}
	
	public boolean deleteUserById(Integer uid) {
		
		if (userRepo.existsById(uid)) {
			userRepo.deleteById(uid);
			return true;
		}
		return false;
	}
	
	
	public List<User> getAllUsers() {
		
		return userRepo.findAll();
	}
}