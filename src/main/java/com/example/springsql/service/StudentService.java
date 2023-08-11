package com.example.springsql.service;

import com.example.springsql.entities.Student;

import java.util.Collection;

public interface StudentService {
    Student addStudent(Student student);
    Student findStudent(long id);
    Student editStudent(long id, Student student);
    void deleteStudent(long id);
    Collection<Student> getAllStudents();
    Collection<Student> findByAgeBetween(int min, int max);
}
