package com.example.springsql.service;

import com.example.springsql.entities.Student;
import com.example.springsql.entities.StudentNameAge;
import com.example.springsql.repositorries.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class StudentServiceImpl implements  StudentService {
    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        logger.debug("Student {} added in database", student);
        return studentRepository.save(student);
    }

    @Override
    public Student findStudent(long id) {
        logger.debug("Student found by {}", id);
        return studentRepository.findById(id).get();
    }

    @Override
    public Student editStudent(long id, Student student) {
        logger.debug("Student {} added in database by {}", student, id);
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        logger.debug("Student deleted by {}", id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        logger.debug("got all students");
       return studentRepository.findAll();
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.debug("got students by age between {} and {}", min, max);
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Integer getCountStudents() {
        logger.debug("got count students");
        return studentRepository.getCountStudentsByAge();
    }

    @Override
    public Integer getAvgStudentByAge() {
        logger.debug("got avg age student's");
        return studentRepository.getAvgStudents();
    }

    @Override
    public List<StudentNameAge> getLastStudent() {
        logger.debug("got last student");
        return studentRepository.getLastStudents();
    }

    @Override
    public List<Student> getStudentsByName(String name) {
        logger.debug("got students by {}", name);
        return studentRepository.getStudentByName(name);
    }
}
