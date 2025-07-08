package com.recordsapi.service;

import com.recordsapi.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StudentServiceTest {
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService();
    }

    @Test
    void getCreateStudent_ShouldAddAndReturnStudent() {
        Student student = studentService.createStudent("Banty", 10, "C");
        assertThat(student.getId()).isEqualTo("1");
        assertThat(student.getName()).isEqualTo("Banty");
        assertThat(student.getAge()).isEqualTo(10);
        assertThat(student.getGrade()).isEqualTo("C");
        List<Student> allStudents = studentService.getAllStudents();
        assertThat(allStudents).containsExactly(student);
    }

    @Test
    void getStudentById_ShouldReturnCorrectStudent() {
        Student student2 = studentService.createStudent("Anand", 32, "C");
        Student found = studentService.getStudentById(student2.getId());
        assertThat(found).isEqualTo(student2);
    }

    @Test
    void getStudentById_ShouldReturnNullIfNotFound() {
        Student result = studentService.getStudentById("3333");
        assertThat(result).isNull();
    }

    @Test
    void getAllStudents_ShouldReturnAllCreatedStudents() {
        Student student1 = studentService.createStudent("Banty", 10, "C");
        Student student2 = studentService.createStudent("Anand", 32, "E");
        List<Student> students = studentService.getAllStudents();
        assertThat(students).containsExactly(student1, student2);
    }
}

