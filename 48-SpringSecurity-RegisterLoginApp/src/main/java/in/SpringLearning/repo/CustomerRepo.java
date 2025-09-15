package in.SpringLearning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.SpringLearning.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{

	Customer findByEmail(String username);

}
