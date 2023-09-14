package com.example.springsql.service;

import com.example.springsql.entities.Student;
import com.example.springsql.entities.StudentNameAge;

import java.util.Collection;
import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    Student findStudent(long id);
    Student editStudent(long id, Student student);
    void deleteStudent(long id);
    Collection<Student> getAllStudents();
    Collection<Student> findByAgeBetween(int min, int max);
    Integer getCountStudents();
    Integer getAvgStudentByAge();
    List<StudentNameAge> getLastStudent();
    List<Student> getStudentsByName(String name);
    List<Student> getStudentByNameWhereNameBeginWithCharacter(String character) throws IllegalArgumentException;
    List<Student> printStudentInConsoleWithThreadWithoutOrder();
    List<Student> printStudentInConsoleWithThreadWithOrder();
}
