package com.recordsapi.service;

import com.recordsapi.exception.StudentNotFoundException;
import com.recordsapi.model.StudentEntity;
import com.recordsapi.repository.StudentRepository;
import com.recordsapi.validation.StudentValidator;
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
    public List<StudentEntity>  getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentEntity getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public StudentEntity createStudent(String name, int age, String grade ) {
        StudentValidator.validateStudentInput(name, age, grade);
        StudentEntity student = StudentEntity.builder()
                .name(name)
                .age(age)
                .grade(grade)
                .build();
        return studentRepository.save(student);
    }
}
