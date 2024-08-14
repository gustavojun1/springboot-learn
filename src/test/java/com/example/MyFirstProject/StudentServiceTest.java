package com.example.MyFirstProject;

import com.example.MyFirstProject.Student.Student;
import com.example.MyFirstProject.Student.StudentRepository;
import com.example.MyFirstProject.Student.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // to setup mockito alongside junit
public class StudentServiceTest {

    @Mock // creates mock of the following dependency
    private StudentRepository studentRepository;

    @InjectMocks // injects into the following object all the dependencies mocked by @Mock (or @Spy), and thus wiring them
    private StudentService studentService;

    @Test
    public void givenStudent_whenEmailNotTaken_thenStudentIsSaved() {
        
        Student expected = new Student("name1", "email1", LocalDate.of(2017, 12, 03));

        // when the method "save" from studentRepository is called on any instance of Student class
        // then, the "save" method should return the "expected" object
        // this way, we're mocking the database to "store" the above created "expected" object
        when(studentRepository.save(Mockito.any(Student.class))).thenReturn(expected);

        // when the method "findStudentByEmail" of "studentRepository" is called specifically with the email of the "expected" object
        // then, return an empty response (i.e., the email was not taken)
        when(studentRepository.findStudentByEmail(expected.getEmail())).thenReturn(Optional.empty());

        studentService.addNewStudent(expected);

        verify(studentRepository).save(expected); // verify if method "save" from "studentRepository" was indeed being called (it's ok since "save" method is from the framework and can be assumed to be fully functional)
    }
}
