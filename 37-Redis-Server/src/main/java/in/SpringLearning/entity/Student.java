package in.SpringLearning.entity;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@Data
@RedisHash("studentHash")
public class Student {
	
	private Integer id ;
	
	private String name;

	
	
	

}
