package in.SpringLearning.service;

import java.util.List;

import in.SpringLearning.entity.User;

public interface UserService {

	Boolean saveUser(User user);

	List<User> getUsers();
	
	

}
