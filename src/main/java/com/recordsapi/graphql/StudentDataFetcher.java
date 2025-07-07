package com.recordsapi.graphql;

import com.netflix.graphql.dgs.*;
import com.recordsapi.model.Student;
import com.recordsapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class StudentDataFetcher {

    @Autowired
    private StudentService studentService;

    @DgsQuery
    public List<Student> allStudents() {
        return studentService.getAllStudents();
    }

    @DgsQuery
    public Student studentById(@InputArgument String id) {
        return studentService.getStudentById(id);
    }

    @DgsMutation
    public Student createStudent(@InputArgument String name, @InputArgument Integer age, @InputArgument String grade) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty");
        }
        if (age == null || age < 5 || age > 100) {
            throw new IllegalArgumentException("Age must be between 5 and 100");
        }
        if (grade == null || grade.trim().isEmpty()) {
            throw new IllegalArgumentException("Grade must not be empty");
        }
        return studentService.createStudent(name, age, grade);
    }
}