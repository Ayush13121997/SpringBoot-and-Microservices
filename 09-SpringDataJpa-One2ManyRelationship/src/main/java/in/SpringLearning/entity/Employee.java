package in.SpringLearning.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer empId;
	
	private String empName;
	
	private Double salary ;
	
	@OneToMany(mappedBy = "employee" ,cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
	private List<Address> address;
	

}
