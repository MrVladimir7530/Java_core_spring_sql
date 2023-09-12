package com.example.springsql.service;

import com.example.springsql.entities.Faculty;

import java.util.Collection;

public interface FacultyService {
    Faculty addFaculty(Faculty faculty);

    Faculty findFaculty(long id);

    Faculty editFaculty(long id, Faculty faculty);

    void deleteFaculty(long id);

    Collection<Faculty> findAllFaculty();

    Collection<Faculty> findAllFacultyByColor(String color);

    Collection<Faculty> findAllFacultyByName(String name);

    Collection<Faculty> getFacultyByNameAndColor(String name, String color);

    String getLongestFaculty();
}
