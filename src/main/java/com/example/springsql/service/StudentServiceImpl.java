package com.example.springsql.service;

import com.example.springsql.entities.Student;
import com.example.springsql.entities.StudentNameAge;
import com.example.springsql.repositorries.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class StudentServiceImpl implements  StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student editStudent(long id, Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
       return studentRepository.findAll();
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Integer getCountStudents() {
        return studentRepository.getCountStudentsByAge();
    }

    @Override
    public Integer getAvgStudentByAge() {
        return studentRepository.getAvgStudents();
    }

    @Override
    public List<StudentNameAge> getLastStudent() {
        return studentRepository.getLastStudents();
    }

    @Override
    public List<Student> getStudentsByName(String name) {
        return studentRepository.getStudentByName(name);
    }
}
