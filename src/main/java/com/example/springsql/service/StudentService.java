package com.example.springsql.service;

import com.example.springsql.entities.AvgStudentByAge;
import com.example.springsql.entities.CountStudent;
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
    List<CountStudent> getCountStudents();
    List<AvgStudentByAge> getAvgStudentByAge();
    List<StudentNameAge> getLastStudent();
}
