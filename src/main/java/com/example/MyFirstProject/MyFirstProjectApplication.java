package com.example.MyFirstProject;

import com.example.MyFirstProject.Student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
@RestController // A Controller receives and redirects the HTTP requests from the client. A Rest Controller is a Controller that returns data in a format that is easily understood by the client, in this case, in JSON.
public class MyFirstProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFirstProjectApplication.class, args);
	}

	@GetMapping("/hello/") // A Get request is used to *retrieve* information from the server. Get Mapping is a Get request that maps a specific URL to a method. Without specifying the URL, the method will be mapped to the root URL (the "home page").
	public List<Student> hello() {
		return List.of(new Student("12542571", "Jun", "gustavonagatomo@gmail.com", LocalDate.of(2003, Month.FEBRUARY, 11), 20));
	}

}
