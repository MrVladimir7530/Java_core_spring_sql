package com.example.springsql.controller;

import com.example.springsql.entities.Faculty;
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
public class FacultyControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void contextLoad() {

    }

    @Test
    public void testGetFacultyById() {
        ResponseEntity<Faculty> newFacultyResponse =
                testRestTemplate.postForEntity("http://localhost:" + port + "/faculty", new Faculty("New faculty", "red"), Faculty.class);

        assertThat(newFacultyResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        Faculty newFaculty = newFacultyResponse.getBody();

        ResponseEntity<Faculty> facultyEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/faculty/" + newFaculty.getId(), Faculty.class);

        Faculty faculty = facultyEntity.getBody();
        assertThat(faculty.getId()).isEqualTo(newFaculty.getId());
        assertThat(faculty.getName()).isEqualTo(newFaculty.getName());
        assertThat(faculty.getColor()).isEqualTo(newFaculty.getColor());
    }

    @Test
    public void testGetAllFaculties() {

        ResponseEntity<Faculty> newFacultyResponse =
                testRestTemplate.postForEntity("http://localhost:" + port + "/faculty",new Faculty("New faculty", "red"), Faculty.class);

        assertThat(newFacultyResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<List> facultyEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/faculty", List.class);
        assertThat(facultyEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(facultyEntity.getBody()).isNotNull();

    }

    @Test
    public void testDeleteFaculties() {
        ResponseEntity<Faculty> newFacultyResponse =
                testRestTemplate.postForEntity("http://localhost:" + port + "/faculty", new Faculty("New faculty", "red"), Faculty.class);

        assertThat(newFacultyResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        Faculty newFaculty = newFacultyResponse.getBody();
   testRestTemplate.delete("http://localhost:" + port + "/faculty/" + newFaculty.getId(), Faculty.class);

        ResponseEntity<Faculty> facultyEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/faculty/" + newFaculty.getId(), Faculty.class);
        Faculty faculty = facultyEntity.getBody();
        assertThat(faculty.getName()).isNull();
        assertThat(faculty.getColor()).isNull();
    }


}
