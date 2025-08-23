package in.SpringLearning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.SpringLearning.entity.User;

@Repository
public interface UserRepo extends  JpaRepository<User, Integer> {

	// No additional methods needed as JpaRepository provides basic CRUD operations
	// and we can add custom queries if required later.
	
	// Example of a custom query method (uncomment if needed):
	// User findByUname(String uname);

}
