package com.example.MyFirstProject.Student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public List<Student> getStudentList() {
		return studentService.getStudents();
	}

	@PostMapping
	public ResponseEntity<Student> registerNewStudent(@RequestBody Student student) {
		return new ResponseEntity<>(studentService.addNewStudent(student), HttpStatus.CREATED);
	}

	@DeleteMapping(path="{studentId}")
	public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") Long studentId) {
		try {
			studentService.deleteStudent(studentId);
			return ResponseEntity.noContent().build();
		} catch (IllegalStateException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path="{studentId}")
	public void updateStudent(@PathVariable("studentId") Long studentId,
							  @RequestParam(required = false) String studentName,
							  @RequestParam(required = false) String studentEmail) {
		studentService.updateStudent(studentId, studentName, studentEmail);
	}
}