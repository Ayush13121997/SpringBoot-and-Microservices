package in.SpringLearning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.SpringLearning.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

}
