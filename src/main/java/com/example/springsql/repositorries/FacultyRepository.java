package com.example.springsql.repositorries;

import com.example.springsql.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findAllByColorIgnoreCase(String color);
    Collection<Faculty>  findAllByNameIgnoreCase(String name);
    Collection<Faculty> getFacultyByNameAndColor(String name, String color);
}
