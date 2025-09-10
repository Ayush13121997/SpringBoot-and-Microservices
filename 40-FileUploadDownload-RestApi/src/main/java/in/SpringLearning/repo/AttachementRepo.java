package in.SpringLearning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.SpringLearning.model.Attachment;

@Repository
public interface AttachementRepo extends JpaRepository<Attachment, String> {

}
