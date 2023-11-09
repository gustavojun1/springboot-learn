package com.example.MyFirstProject.Student;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@GetMapping("hello/") // A Get request is used to *retrieve* information from the server. Get Mapping is a Get request that maps a specific URL to a method. Without specifying the URL, the method will be mapped to the root URL (the "home page").
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if(studentOptional.isPresent())
			throw new IllegalStateException("Email already taken");
		studentRepository.save(student);
	}

	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if(!exists)
			throw new IllegalStateException("Student with id " + studentId + " does not exist");
		studentRepository.deleteById(studentId);
	}
}
