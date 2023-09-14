package com.example.springsql.repositorries;

import com.example.springsql.entities.Student;
import com.example.springsql.entities.StudentNameAge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> getStudentByName(String name);
    Collection<Student> findByAgeBetween(int min, int max);

    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Integer getCountStudentsByAge();

    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    Integer getAvgStudents();

    @Query(value = "SELECT name, age FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<StudentNameAge> getLastStudents();

}
