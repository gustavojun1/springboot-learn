package com.example.MyFirstProject.Student;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {
    @GetMapping("hello/") // A Get request is used to *retrieve* information from the server. Get Mapping is a Get request that maps a specific URL to a method. Without specifying the URL, the method will be mapped to the root URL (the "home page").
	public List<Student> getStudents() {
		return List.of(
				new Student("12542571", "Jun", "gustavonagatomo@gmail.com", LocalDate.of(2003, Month.FEBRUARY, 11), 20),
				new Student( "32131", "sdahfja", "fdisajfi@gmail.com", LocalDate.of(1998, Month.JUNE, 4), 25));
	}
}
