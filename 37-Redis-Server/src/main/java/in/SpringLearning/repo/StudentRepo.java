package in.SpringLearning.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.SpringLearning.entity.Student;

@Repository
public interface StudentRepo extends CrudRepository<Student, Integer> {

}
