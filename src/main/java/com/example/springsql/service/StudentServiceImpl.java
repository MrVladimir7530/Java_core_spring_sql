package com.example.springsql.service;

import com.example.springsql.entities.Student;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class StudentServiceImpl implements  StudentService {
    private final HashMap<Long, Student> students = new HashMap<>();
    private long count = 0;
    @Override
    public Student addStudent(Student student) {
        student.setId(count++);
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Student findStudent(long id) {
        return students.get(id);
    }

    @Override
    public Student editStudent(long id, Student student) {
        if (!students.containsKey(id)) {
            return null;
        }
        students.put(id, student);
        return student;
    }

    @Override
    public void deleteStudent(long id) {
        students.remove(id);
    }
}