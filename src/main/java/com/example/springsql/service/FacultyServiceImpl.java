package com.example.springsql.service;

import com.example.springsql.entities.Faculty;
import com.example.springsql.repositorries.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty editFaculty(long id, Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }
    @Override
    public Collection<Faculty> findAllFaculty() {
        return facultyRepository.findAll();
    }
    @Override
    public Collection<Faculty> findAllFacultyByColor(String color) {
        return  facultyRepository.findAllByColorIgnoreCase(color);
    }
    @Override
    public Collection<Faculty> findAllFacultyByName(String name) {
        return facultyRepository.findAllByNameIgnoreCase(name);
    }

    @Override
    public Collection<Faculty> getFacultyByNameAndColor(String name, String color) {
        return facultyRepository.getFacultyByNameAndColor(name, color);
    }
}
