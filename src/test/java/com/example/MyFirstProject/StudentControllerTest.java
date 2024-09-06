package com.example.MyFirstProject;

import com.example.MyFirstProject.Student.Student;
import com.example.MyFirstProject.Student.StudentController;
import com.example.MyFirstProject.Student.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(controllers = StudentController.class) // autoconfigure tests that focuses only on Spring MVC components
@AutoConfigureMockMvc(addFilters = false) // to avoid token security issues (otherwise @WebMvcTest already configures MockMvc)
@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    // @Autowired does not work because this is a POJO (Plain Old Java Object), not a specialized one that Spring can manage like controllers or services
    private Student student;

    @Test
    public void givenStudent_whenRegisterNewStudent_thenAddNewStudent() throws Exception {
        student = new Student("name1", "email1", LocalDate.of(2017, 12, 03));

        // given() is a more "BDD way" to write mocks than when().then() (though both achieve the same results)
        given(
                studentService.addNewStudent(
                        ArgumentMatchers.any() // accepts calls with input of any class/type
                )
        ).willAnswer(
                (invocation -> invocation.getArgument(0)) // returns the first argument passed (in this case, the Student object)
        );

        ResultActions response = mockMvc.perform( // simulates a HTTP (post) request
                post("/api/v1/student/")
                        .contentType(MediaType.APPLICATION_JSON) // sets the header of the request to "application/json", informing the server that the request contain data in JSON format
                        .content(objectMapper.writeValueAsString(student)) // sets the body of the request to be the student object in JSON format
        );

        // asserts whether the HTTP response code is "201 Created"
        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }
}