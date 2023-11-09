package com.example.MyFirstProject.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    /*
        The annotation above is the JPQL (Java Persistence Query Language) equivalent to the following SQL query:
        SELECT * FROM student WHERE email = ?
    */
    Optional<Student> findStudentByEmail(String email);
}