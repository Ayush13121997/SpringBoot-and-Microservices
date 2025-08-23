package in.SpringLearning.service;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.SpringLearning.entity.Passport;
import in.SpringLearning.entity.Person;
import in.SpringLearning.repo.PassportRepo;
import in.SpringLearning.repo.PersonRepo;

@Service
public class PersonPassportService {

	private PersonRepo personRepo;

	@Autowired
	public PersonPassportService(PersonRepo personRepo) {
		this.personRepo = personRepo;
	}
	
	public void savePersonWithPassport()
	{
		Passport pp = new Passport();
		
		pp.setPassportNum("J8I9DDA1E");
		pp.setIssuedDt(LocalDate.now());
		pp.setExpiryDt(LocalDate.now().plusYears(10));
		
		Person person =new Person();
		person.setName("Ayush");
		person.setGender("M");
		
		
		//associating entities
		pp.setPerson(person);
		person.setPassport(pp);
		
		personRepo.save(person);
		
		
		
	}
	
}
