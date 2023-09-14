package com.example.springsql.service;

import com.example.springsql.entities.Faculty;
import com.example.springsql.repositorries.FacultyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;
    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        logger.debug("Faculty added {}", faculty);
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(long id) {
        logger.debug("got faculty by {}", id);
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty editFaculty(long id, Faculty faculty) {
        logger.debug("Faculty added {} by {}", faculty, id);
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long id) {
        logger.debug("deleted faculty by {}", id);
        facultyRepository.deleteById(id);
    }
    @Override
    public Collection<Faculty> findAllFaculty() {
        logger.debug("got faculties");
        return facultyRepository.findAll();
    }

    @Override
    public Collection<Faculty> findAllFacultyByColor(String color) {
        logger.debug("got all faculties by {}", color);
        return facultyRepository.findAllByColorIgnoreCase(color);
    }
    @Override
    public Collection<Faculty> findAllFacultyByName(String name) {
        logger.debug("got faculties by {}", name);
        return facultyRepository.findAllByNameIgnoreCase(name);
    }

    @Override
    public Collection<Faculty> getFacultyByNameAndColor(String name, String color) {
        logger.debug("got faculties by {} and {}", name, color);
        return facultyRepository.getFacultyByNameAndColor(name, color);
    }

    @Override
    public String getLongestFaculty() {
        logger.debug("got longest faculty");
        List<Faculty> facultyList = facultyRepository.findAll();
        return facultyList.stream().parallel()
                .max((x, y) -> x.getName().length() - y.getName().length())
                .orElseThrow(() -> new RuntimeException())
                .getName();
    }
}
