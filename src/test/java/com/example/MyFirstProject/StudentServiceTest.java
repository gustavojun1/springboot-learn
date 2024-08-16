package com.example.MyFirstProject;

import com.example.MyFirstProject.Student.Student;
import com.example.MyFirstProject.Student.StudentRepository;
import com.example.MyFirstProject.Student.StudentService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.checkerframework.checker.nullness.Opt;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // to setup mockito alongside junit
public class StudentServiceTest {

    @Mock // creates mock of the following dependency
    private StudentRepository studentRepository;

    @InjectMocks // injects into the following object all the dependencies mocked by @Mock (or @Spy), and thus wiring them
    private StudentService studentService;

    @Mock
    private Student student;

    @Test
    public void givenStudentNotPresent_whenAddNewStudent_thenStudentIsSaved() {
        
        // when the method "save" from studentRepository is called on any instance of Student class
        // then, the "save" method should return the "student" object
        // this way, we're mocking the database to "store" the above created "student" object
        when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);

        // when the method "findStudentByEmail" of "studentRepository" is called specifically with the email of the "student" object
        // then, return an empty response (i.e., the email was not taken)
        when(studentRepository.findStudentByEmail(student.getEmail())).thenReturn(Optional.empty());

        studentService.addNewStudent(student);

        verify(studentRepository).save(student); // verify if method "save" from "studentRepository" was indeed being called (it's ok since "save" method is from the framework and can be assumed to be fully functional)
    }

    @Test
    public void givenStudentPresent_whenAddNewStudent_thenThrowIllegalStateException() {

        // when "studentRepository" calls "findStudentByEmail" method with exactly "email1" as parameter, then return "student", as if it's already in the database
        when(studentRepository.findStudentByEmail(student.getEmail())).thenReturn(Optional.of(student));

        Assertions.assertThrows(IllegalStateException.class,() -> studentService.addNewStudent(student));

    }

    @Test
    public void givenStudentNotPresent_whenDeleteStudent_thenThrowIllegalStateException() {

        when(studentRepository.existsById(student.getId())).thenReturn(false);

        Assert.assertThrows(IllegalStateException.class, () -> studentService.deleteStudent(student.getId()));

    }

    @Test
    public void givenStudentPresent_whenDeleteStudent_thenDeleteStudent() {

        when(studentRepository.existsById(student.getId())).thenReturn(true);

        studentService.deleteStudent(student.getId());

        verify(studentRepository).deleteById(student.getId());

    }

    @Test
    public void givenStudentNotPresent_whenUpdateStudent_thenThrowIllegalStateException() {

        when(student.getName()).thenReturn("name1");
        when(student.getEmail()).thenReturn("email1");

        when(studentRepository.findById(student.getId())).thenReturn(Optional.empty());

        // with the same data
        Assertions.assertThrows(IllegalStateException.class, () -> studentService.updateStudent(student.getId(), student.getName(), student.getEmail()));

        // with new data
        Assertions.assertThrows(IllegalStateException.class, () -> studentService.updateStudent(student.getId(), "another name", "another email"));
    }

    @Test
    public void givenStudentPresent_whenUpdateStudentWithNoChanges_thenNoChangesApplied() {

        when(student.getName()).thenReturn("name1");
        when(student.getEmail()).thenReturn("email1");

        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));

        studentService.updateStudent(student.getId(), student.getName(), student.getEmail());

        verify(student, Mockito.never()).setName(Mockito.any(String.class));
        verify(student, Mockito.never()).setEmail(Mockito.any(String.class));
        verify(studentRepository, Mockito.never()).save(Mockito.any(Student.class));

    }

    @Test
    public void givenStudentPresent_whenUpdateName_thenOnlyNameUpdated() {

        student = new Student("name1", "email1", LocalDate.of(2017, 12, 03));

        String new_name = "new name";

        Student prev = new Student(student.getName(), student.getEmail(), student.getDob());

        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));

        studentService.updateStudent(student.getId(), new_name, student.getEmail());

        Assertions.assertEquals(prev.getEmail(), student.getEmail());
        Assertions.assertEquals(prev.getDob(), student.getDob());
        Assertions.assertEquals(new_name, student.getName());

    }

    @Test
    public void givenStudentPresent_whenUpdateEmail_thenOnlyEmailUpdated() {

        student = new Student("name1", "email1", LocalDate.of(2017, 12, 03));

        String new_email = "new email";

        Student prev = new Student(student.getName(), student.getEmail(), student.getDob());

        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));


        studentService.updateStudent(student.getId(), student.getName(), new_email);

        Assertions.assertEquals(prev.getName(), student.getName());
        Assertions.assertEquals(prev.getDob(), student.getDob());
        Assertions.assertEquals(new_email, student.getEmail());

    }

    @Test
    public void givenStudentPresent_whenUpdateEmailAlreadyTaken_thenThrowIllegalStateException() {

        when(student.getEmail()).thenReturn("email1");

        Student student2 = new Student("name2", "email2", LocalDate.of(2002, 12, 11));

        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));

        when(studentRepository.findStudentByEmail(student2.getEmail())).thenReturn(Optional.of(student2));

        Assertions.assertThrows(IllegalStateException.class, () -> studentService.updateStudent(student.getId(), student.getName(), student2.getEmail()));

    }
}