package in.SpringLearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.SpringLearning.entity.Student;
import in.SpringLearning.repo.StudentRepo;

@RestController
public class StudentRestController {
	
	@Autowired
	private StudentRepo studentRepo ;
	
	@PostMapping("/student")
	public String addStudent(@RequestBody Student student) {
		
		studentRepo.save(student);
		
		return "Student Saved" ;
	}
	
	@GetMapping("/students")
	public Iterable<Student> getAllStudents(){
		
		Iterable<Student> students = studentRepo.findAll();
		
		return students;
	}

}
