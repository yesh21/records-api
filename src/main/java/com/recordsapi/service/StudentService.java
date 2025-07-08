package com.recordsapi.service;

import com.recordsapi.model.Student;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private int idCounter = 1;

    public List<Student>  getAllStudents() {
        return students;
    }

    public Student getStudentById(String id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Student createStudent(String name, int age, String grade ) {
        Student newStudent = new Student(String.valueOf(idCounter++), name, age, grade);
        students.add(newStudent);
        return newStudent;
    }
}
