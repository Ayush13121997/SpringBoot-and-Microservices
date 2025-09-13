package in.SpringLearning.batchProcessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import in.SpringLearning.dto.Person;

public class PersonItemProcessor implements ItemProcessor<Person, Person>{

	 private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);
	
	@Override
	public Person process(Person item) throws Exception {
		
		
		final String firstName= item.firstName().toUpperCase();
		final String lastName = item.lastName().toUpperCase();
		
		final Person transformedPerson = new Person(firstName ,lastName);
		
		return transformedPerson;
	}
	
	

}
