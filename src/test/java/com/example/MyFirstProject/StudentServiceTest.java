package com.example.MyFirstProject;

import com.example.MyFirstProject.Student.Student;
import com.example.MyFirstProject.Student.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentService studentService;

    @Test
    public void givenStudent_whenAddNewStudent_thenReturnStudent() {
        
        Student expected = new Student("name1", "email1", LocalDate.of(2017, 12, 03));
        
        studentService.addNewStudent(expected);
        List<Student> student_list = studentService.getStudents();

        Assertions.assertThat(student_list).contains(expected);
    }
}
