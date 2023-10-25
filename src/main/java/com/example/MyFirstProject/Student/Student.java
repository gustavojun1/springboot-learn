package com.example.MyFirstProject.Student;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data // getters and setters
@AllArgsConstructor // constructor
public class Student {
    private String id;
    private String name;
    private String email;
    private LocalDate dob; // date of birth
    private Integer age;
}
