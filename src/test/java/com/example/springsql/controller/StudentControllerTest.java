package com.example.springsql.controller;

import com.example.springsql.entities.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void contextLoad() {
    }

    @Test
    public void testGetStudentById() {
        ResponseEntity<Student> newStudentResponse =
                testRestTemplate.postForEntity("http://localhost:" + port + "/student", new Student("VovaTest", 100), Student.class);

        assertThat(newStudentResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        Student newStudent = newStudentResponse.getBody();

        ResponseEntity<Student> responseEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/student/" + newStudent.getId(), Student.class);

        Student student = responseEntity.getBody();
        assertThat(student.getId()).isEqualTo(newStudent.getId());
        assertThat(student.getName()).isEqualTo(newStudent.getName());
        assertThat(student.getAge()).isEqualTo(newStudent.getAge());
    }
    @Test
    public void testGetAllStudents() {
        ResponseEntity<Student> newStudentResponse =
                testRestTemplate.postForEntity("http://localhost:" + port + "/student", new Student("VovaTest", 100), Student.class);

        assertThat(newStudentResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<List> responseEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/student", List.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void testDeleteStudents() {
        ResponseEntity<Student> newStudentResponse =
                testRestTemplate.postForEntity("http://localhost:" + port + "/student", new Student("VovaTest", 100), Student.class);

        assertThat(newStudentResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        Student newStudent = newStudentResponse.getBody();
        testRestTemplate.delete("http://localhost:" + port + "/student/" + newStudent.getId(), Student.class);

        ResponseEntity<Student> responseEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/student/" + newStudent.getId(), Student.class);

        Student student = responseEntity.getBody();
        assertThat(student.getName()).isNull();
        assertThat(student.getAge()).isEqualTo(0);
    }

}
