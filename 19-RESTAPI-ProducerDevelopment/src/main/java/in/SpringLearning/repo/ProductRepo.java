package in.SpringLearning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.SpringLearning.Entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

}
