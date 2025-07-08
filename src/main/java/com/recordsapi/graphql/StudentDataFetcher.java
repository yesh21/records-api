package com.recordsapi.graphql;

import com.netflix.graphql.dgs.*;
import com.recordsapi.model.Student;
import com.recordsapi.service.StudentService;
import com.recordsapi.validation.StudentValidator;

import java.util.List;

@DgsComponent
public class StudentDataFetcher {

    private final StudentService studentService;

    public StudentDataFetcher(StudentService studentService) {
        this.studentService = studentService;
    }

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
        StudentValidator.validateStudentInput(name, age, grade);
        return studentService.createStudent(name, age, grade);
    }
}