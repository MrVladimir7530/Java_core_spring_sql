package com.example.springsql.service;

import com.example.springsql.entities.Faculty;
import com.example.springsql.repositorries.FacultyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FacultyServiceTest {
    private FacultyService facultyService;
    private FacultyRepository facultyRepository;

    @BeforeEach
    public void setUp() {
        facultyRepository = Mockito.mock(FacultyRepository.class);
        facultyService = new FacultyServiceImpl(facultyRepository);
    }

    @Test
    public void addFacultyTest() {
        assertEquals("name", "name");
        Faculty faculty = new Faculty("faculty1", "red");
        when(facultyRepository.save(faculty)).thenReturn(faculty);
        assertEquals(facultyService.addFaculty(faculty), faculty);
    }

//    @Test
//    public void findFaculty() {
//        long id = 0;
//        Faculty faculty = new Faculty(0,"faculty1", "red");
//        when(facultyRepository.findById(id).get()).thenReturn(faculty);
//        assertEquals(facultyService.findFaculty(id).getName(), faculty.getName());
//    }

}
