package com.example.MyFirstProject.repository;


import com.example.MyFirstProject.Student.Student;
import com.example.MyFirstProject.Student.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest // to specify a test on a repository class
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test // to define a test
    public void givenEmail_whenFindStudentByEmail_thenReturnStudent() {

        // arrange
        String email = "email1";
        Student expected = new Student("name1", email, LocalDate.of(2017, 12, 03));
        studentRepository.save(expected);

        // act
        Optional<Student> actual = studentRepository.findStudentByEmail(email);

        // assert
        Assertions.assertThat(actual).isPresent();
        Assertions.assertThat(actual.get().getId()).isGreaterThan(0);
    }
}
