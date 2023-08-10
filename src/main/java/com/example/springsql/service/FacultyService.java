package com.example.springsql.service;

import com.example.springsql.entities.Faculty;

public interface FacultyService {
    Faculty addFaculty(Faculty faculty);
    Faculty findFaculty(long id);
    Faculty editFaculty(long id, Faculty faculty);
    void deleteFaculty(long id);
}
