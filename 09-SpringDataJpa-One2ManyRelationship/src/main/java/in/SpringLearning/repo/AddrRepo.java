package in.SpringLearning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.SpringLearning.entity.Address;

@Repository
public interface AddrRepo extends JpaRepository<Address, Integer>{

}
