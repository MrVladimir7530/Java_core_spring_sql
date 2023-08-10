package com.example.springsql.service;

import com.example.springsql.entities.Student;

public interface StudentService {
    Student addStudent(Student student);
    Student findStudent(long id);
    Student editStudent(long id, Student student);
    void deleteStudent(long id);
}
