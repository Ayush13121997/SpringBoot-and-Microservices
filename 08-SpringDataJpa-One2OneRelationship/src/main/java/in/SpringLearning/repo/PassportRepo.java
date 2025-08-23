package in.SpringLearning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.SpringLearning.entity.Passport;

@Repository
public interface PassportRepo extends JpaRepository<Passport, Integer> {

}
