package com.recordsapi.service;

import com.recordsapi.model.Student;
import com.recordsapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Student>  getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student createStudent(String name, int age, String grade ) {
        Student student = Student.builder()
                .name(name)
                .age(age)
                .grade(grade)
                .build();
        return studentRepository.save(student);
    }
}
