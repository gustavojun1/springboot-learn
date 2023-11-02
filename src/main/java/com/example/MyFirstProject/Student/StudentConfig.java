package com.example.MyFirstProject.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student Jorge = new Student(
                "Jorge",
                "sdfadasdf@gmail.com",
                LocalDate.of(1998, Month.JANUARY, 5)
            );

            Student Rose = new Student(
                    "Rose",
                    "rosenagatomo@yahoo.com.br",
                    LocalDate.of(1972, Month.APRIL, 28)
            );

            Student Jun = new Student(
                    "Jun",
                    "gustavonagatomo@gmail.com",
                    LocalDate.of(2003, Month.FEBRUARY, 11)
            );

            repository.saveAll(List.of(Jorge, Jun, Rose));
        };
    }
}
