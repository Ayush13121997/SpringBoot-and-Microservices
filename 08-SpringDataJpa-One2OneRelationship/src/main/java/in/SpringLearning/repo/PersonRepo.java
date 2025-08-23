package in.SpringLearning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.SpringLearning.entity.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer>{

}
