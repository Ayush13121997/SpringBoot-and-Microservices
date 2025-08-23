package in.SpringLearning.entity;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name ="ORDR_DTLS")
@Data
public class OrderDetails {

	@Id
	@GenericGenerator(name="orderId_generator" ,strategy = "in.SpringLearning.generator.OrderIdGenerator")
	@GeneratedValue(generator = "orderId_generator")
	@Column(name ="ORDR_ID")
	private String orderId ;
	
	@Column(name ="ORDR_BY")
	private String orderBy ;
	
	@Column(name ="ORDR_PLCD_DT")
	private Date orderPlacesDate ;
}
