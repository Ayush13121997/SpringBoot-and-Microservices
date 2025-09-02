package in.SpringLearning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import in.SpringLearning.entity.Student;

@RepositoryRestResource
public interface StudentRepo extends JpaRepository<Student, Long> {

}
