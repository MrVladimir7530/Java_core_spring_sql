package com.example.springsql.controller;

import com.example.springsql.entities.Faculty;
import com.example.springsql.repositorries.FacultyRepository;
import com.example.springsql.repositorries.StudentRepository;
import com.example.springsql.service.*;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
public class FacultyControllerWithMockTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @SpyBean
    private FacultyServiceImpl facultyService;
    @InjectMocks
    private FacultyController facultyController;


    @Test
    public void saveFacultyTest() throws Exception {
        final String name = "facultyTest";
        final String color = "redTest";

        final String name2 = "facultyTest";
        final String color2 = "redTest";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        JSONObject facultyObject2 = new JSONObject();
        facultyObject2.put("name", name2);
        facultyObject2.put("color", color2);

        Faculty faculty = new Faculty();
        faculty.setId(1);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));

        faculty.setName(name2);
        faculty.setColor(color2);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty/1")
                        .content(facultyObject2.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value(name2))
                .andExpect(jsonPath("$.color").value(color2));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
