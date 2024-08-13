package com.example.MyFirstProject;

import com.example.MyFirstProject.Student.Student;
import com.example.MyFirstProject.Student.StudentRepository;
import com.example.MyFirstProject.Student.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // to setup mockito alongside junit
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentService studentService;

    @Test
    public void givenStudent_whenEmailNotTaken_thenStudentIsSaved() {
        
        Student expected = new Student("name1", "email1", LocalDate.of(2017, 12, 03));
        when(studentRepository.findAll()).thenReturn();

        studentService.addNewStudent(expected);

        Assertions.assertThat(student_list).contains(expected);
    }
}
