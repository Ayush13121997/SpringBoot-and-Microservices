package in.SpringLearning.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "USER_DETAILS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;
	
	@NotEmpty(message = "Username cannot be empty")
	private String uname;
	
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
	private String email;
	
    @NotNull(message = "Phone number cannot be null")
	private Long phno;
}
