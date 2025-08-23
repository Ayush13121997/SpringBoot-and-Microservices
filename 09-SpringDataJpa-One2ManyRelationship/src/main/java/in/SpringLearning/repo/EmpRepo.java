package in.SpringLearning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.SpringLearning.entity.Employee;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Integer> {

}
