package in.SpringLearning.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Passport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer passportId;
	
	private String passportNum;
	
	private LocalDate issuedDt;
	
	private LocalDate expiryDt ;
	
	@OneToOne
	@JoinColumn(name="personId")
	private Person person ;
}
