package com.example.MyFirstProject.Student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // A Controller receives and redirects the HTTP requests from the client. A Rest Controller is a Controller that returns data in a format that is easily understood by the client, in this case, in JSON.
@RequestMapping(path = "api/v1/student/") // definition of the request route
public class StudentController {
	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping
	public List<Student> getStudentService() {
		return studentService.getStudents();
	}
}
