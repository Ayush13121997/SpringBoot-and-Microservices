package in.SpringLearning.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Attachment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@GenericGenerator(name = "UUID" ,strategy = "UUID2")
	private String id;
	
	private String fileName;
	
	private String FileType;

	@Lob
	private byte[] imgData;

	public Attachment(String fileName, String fileType, byte[] imgData) {
		super();
		this.fileName = fileName;
		FileType = fileType;
		this.imgData = imgData;
	}

	
}
